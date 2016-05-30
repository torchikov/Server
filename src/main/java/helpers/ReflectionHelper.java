package helpers;


import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.jetbrains.annotations.Nullable;

import java.lang.reflect.Field;

public class ReflectionHelper {
    static final Logger logger = LogManager.getLogger(ReflectionHelper.class.getName());



    @Nullable
    public static Object createObject(String className) {
        try {
            return Class.forName(className).newInstance();
        } catch (InstantiationException | IllegalAccessException | ClassNotFoundException e) {
            logger.warn(e.getMessage());
            return null;
        }
    }

    public static void setFieldValue(Object object, String fieldName, String value){
        try {
            Field field = object.getClass().getDeclaredField(fieldName);
            field.setAccessible(true);

            if (field.getType().equals(String.class)){
                field.set(object, value);
            } else if (field.getType().equals(int.class)){
                field.set(object, Integer.decode(value));
            }

            field.setAccessible(false);
        } catch (NoSuchFieldException | IllegalAccessException e) {
            logger.warn(e.getMessage());
            e.printStackTrace();
        }
    }
}
