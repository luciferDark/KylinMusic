package com.kylin.hotfix;

import android.content.Context;
import android.util.Log;

import java.io.File;
import java.lang.reflect.Array;
import java.lang.reflect.Field;
import java.util.HashSet;

import dalvik.system.DexClassLoader;
import dalvik.system.PathClassLoader;

/**
 * Created by kylin on 2018/6/18.
 */

public class HotFixEngine {
    public static final String SUFFIX_DEX = ".dex";
    public static final String SUFFIX_JAR = ".jar";
    public static final String SUFFIX_APK = ".apk";
    public static final String SUFFIX_ZIP = ".zip";


    public static final String DIR_FIX_DEX = "fixDex";
    public static final String DIR_OPTIMIZE_DEX = "optimize_dex";

    public static final String BASE_DEX_CLASS_LOADER = "dalvik.system.BaseDexClassLoader";
    public static final String FIELD_PATHLIST = "pathList";
    public static final String FIELD_DEXELEMENT = "dexElements";

    public static HashSet<File> loadedDexs = new HashSet<>();


    static {
        loadedDexs.clear();
    }

    public HotFixEngine() {
    }

    public static void loadFixedDexs(Context ctx, File patchFilesDir) {
        if (ctx == null) {
            return;
        }
        Log.d("kylin", "patchFilesDir:" + patchFilesDir.getAbsolutePath());
        File fixedDexDir = patchFilesDir != null ? patchFilesDir : new File(ctx.getFilesDir(), DIR_FIX_DEX);
        File[] fixedDirlist = fixedDexDir.listFiles();

        for (File file : fixedDirlist) {
            if (file.getName().startsWith("classes") &&
                    (file.getName().endsWith(SUFFIX_DEX)
                            || file.getName().endsWith(SUFFIX_APK)
                            || file.getName().endsWith(SUFFIX_ZIP)
                            || file.getName().endsWith(SUFFIX_JAR))) {
                Log.d("kylin", "file:" + file.getAbsolutePath() + "==:" + file.exists());
                loadedDexs.add(file);
            }
        }

        //dex合并
        doDexInject(ctx, loadedDexs);
    }

    private static void doDexInject(Context ctx, HashSet<File> loadedDexs) {
        Log.d("kylin", "doDexInject:" );
        String optimizeDir = ctx.getFilesDir().getAbsolutePath() + File.separator + DIR_OPTIMIZE_DEX ;
        Log.d("kylin", "optimizeDir:"  + optimizeDir);
        File optFile = new File(optimizeDir);
        if (!optFile.exists()) {
            return;
        }

        try {
            //1.加载应用程序的dex
            PathClassLoader pathLoader = (PathClassLoader) ctx.getClassLoader();
            // 2.加载指定的修复的dex文件
            for (File dexFile : loadedDexs) {
                DexClassLoader dexLoader = new DexClassLoader(
                        optFile.getAbsolutePath(),//补丁在的路径
                        optFile.getAbsolutePath(),//存放dex的解压目录（用于jar， zip， apk模式）
                        null,//加载dex时需要的库
                        pathLoader//父类加载器
                );
                //3、合并
                Object pathPathList = getPathList(pathLoader);
                Object dexPathList = getPathList(dexLoader);

                Object pathDexElement = getDexElement(pathPathList);
                Object dexDexElement = getDexElement(dexPathList);

                Object dexElementCombine = combineElement(dexDexElement, pathDexElement);

                Object pathList = getPathList(pathLoader);
                setField(pathList, pathList.getClass(), FIELD_DEXELEMENT, dexElementCombine);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

//    private static void existFix(Context ctx){
//        String optimizeDir = ctx.getFilesDir().getAbsolutePath() + File.separator + DIR_OPTIMIZE_DEX;
//        Log.d("kylin", "optimizeDir:"  + optimizeDir);
//        File optFile = new File(optimizeDir);
//        if (!optFile.exists()) {
//            optFile.mkdirs();
//        }
//    }

    /**
     * 合并修复后的dex和apk中dex
     *
     * @param dexDexElement
     * @param pathDexElement
     * @return
     */
    private static Object combineElement(Object dexDexElement, Object pathDexElement) {
        Class componentType = dexDexElement.getClass().getComponentType();
        int dexDexElementLength = Array.getLength(dexDexElement);
        int pathDexElementLength = Array.getLength(pathDexElement);

        int combineLength = dexDexElementLength + pathDexElementLength;

        Object result = Array.newInstance(componentType, combineLength);

        System.arraycopy(dexDexElement, 0,
                result, 0, dexDexElementLength);
        System.arraycopy(pathDexElement, 0 ,
                result, dexDexElementLength, pathDexElementLength);
        return  result;
    }

    /**
     * 反射获取类加载器中pathList对象
     *
     * @param dexClassLoader
     */
    private static Object getPathList(Object dexClassLoader)
            throws ClassNotFoundException, NoSuchFieldException, IllegalAccessException {
        return getField(dexClassLoader, Class.forName(BASE_DEX_CLASS_LOADER),
                FIELD_PATHLIST);
    }

    /**
     * 反射获取类加载器中dexElement象
     *
     * @param pathlist
     */
    private static Object getDexElement(Object pathlist)
            throws ClassNotFoundException, NoSuchFieldException, IllegalAccessException {
        return getField(pathlist, pathlist.getClass(), FIELD_DEXELEMENT);
    }

    /**
     * 反射获取field
     *
     * @param obj
     * @param clazz
     * @param field
     * @return
     * @throws NoSuchFieldException
     * @throws IllegalAccessException
     */
    private static Object getField(Object obj, Class<?> clazz, String field)
            throws NoSuchFieldException, IllegalAccessException {
        Field localField = clazz.getDeclaredField(field);
        localField.setAccessible(true);

        return localField.get(obj);
    }

    /**
     * 反射设置值
     *
     * @param obj
     * @param clazz
     * @param field
     * @param value
     * @throws NoSuchFieldException
     * @throws IllegalAccessException
     */
    private static void setField(Object obj, Class<?> clazz, String field, Object value)
            throws NoSuchFieldException, IllegalAccessException {
        Field declareField = clazz.getDeclaredField(field);
        declareField.setAccessible(true);
        declareField.set(obj, value);
    }
}
