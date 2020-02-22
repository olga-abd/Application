public enum AppExceptionEnum {
    NOSTAFF ("Пользователь не найден"),
    NOCOURSE ("Курс не найден"),
    NOAPP ("Заявка не найдена"),
    INCPSW ("Введен неверный пароль"),
    MAXCOUNT("Запись на курс невозможна (заняты все места)");

    private String description;

    AppExceptionEnum (String description){this.description = description;}

    public String getDescription(){return description;}
}
