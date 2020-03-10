package pkg.exception;

public enum AppExceptionEnum {
    NOSTAFF ("Пользователь не найден"),
    NOCOURSE ("Курс не найден"),
    NOAPP ("Заявка не найдена"),
    INCPSW ("Неверный логин или пароль"),
    MAXCOUNT("Запись на курс невозможна");

    private String description;

    AppExceptionEnum (String description){this.description = description;}

    public String getDescription(){return description;}
}
