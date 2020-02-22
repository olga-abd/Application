public enum ApplicationStatus {

    GENERATED ("Заявка создана"),
    AGREED ("Заявка согласована руководителем"),
    DISAGREED ("Заявка отклонена руководителем"),
    CLOSED ("Произведена запись на курс"),
    NOVACANT ("Запись закрыта по причине отсутствия мест"),
    REJECT ("Заявка отклонена сотрудником hr"),
    BADDATE ("Курс уже начался");

    private String description;

    ApplicationStatus (String description) {this.description = description;}

    public String getDescription(){return description;}
}
