public enum CourseStatus {

    REGISTERED ("Работник зарегистрирован на курс"),
    INPROGRESS ("Стадия обучения"),
    COMPLETED ("Курс завершен");

    private String description;

    CourseStatus (String description){
        this.description = description;
    }

    public String getDescription() {return description;}
}
