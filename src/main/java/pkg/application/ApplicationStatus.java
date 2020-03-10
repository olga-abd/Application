package pkg.application;

public enum ApplicationStatus {

    GENERATED ("Заявка создана"), //0
    AGREED ("Заявка согласована руководителем"), //1
    DISAGREED ("Заявка отклонена руководителем"), //2 
    CLOSED ("Произведена запись на курс"), //3
    NOVACANT ("Все места на курс заняты"), //4
    REJECT ("Заявка отклонена сотрудником hr"), //5
    BADDATE ("Курс уже начался"), //6
    EXCEEDSUM ("Сумма по сотруднику исчерпана"), //7
    EXTERNAL ("Внешнее согласование"); //8

    private String description;

    ApplicationStatus (String description) {this.description = description;}

    public String getDescription(){return description;}
}
