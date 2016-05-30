package helpers;


public enum Types {
    BYTE,
    SHORT,
    CHAR,
    INT,
    LONG,
    FLOAT,
    DOUBLE,
    BOOLEAN,
    STRING;

    public static Types getType(Class<?> clazz){
        String className = clazz.getSimpleName().toUpperCase();
        return Types.valueOf(className);
    }


}
