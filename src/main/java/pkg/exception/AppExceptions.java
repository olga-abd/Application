package pkg.exception;

public class AppExceptions extends Exception {
    public AppExceptions (String code, String msg, Throwable e) {
        super(AppExceptionEnum.valueOf(code).getDescription() + ":" + msg, e);
    }

    public AppExceptions (String code, Throwable e) {
        super(AppExceptionEnum.valueOf(code).getDescription(), e);
    }

    public AppExceptions (String code) {
        super(AppExceptionEnum.valueOf(code).getDescription());
    }
}
