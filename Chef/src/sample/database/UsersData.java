package sample.database;

public class UsersData {
    private int id;
    private String username;
    private String password;
    private boolean administrator;
    private int expenses;
    private String menu;

    public UsersData(int id, String username, String password, boolean administrator) {
        this.id = id;
        this.username = username;
        this.password = password;
        this.administrator = administrator;
    }
    public UsersData(String username, String menu, int expenses){
        this.username = username;
        this.menu = menu;
        this.expenses = expenses;
    }
    public String getUsername() {return username;}
    public void setUsername(String username) {this.username = username;}
    public String getPassword() {return password;}
    public void setPassword(String password) {this.password = password;}
    public boolean isAdministrator() {return administrator;}
    public void setAdministrator(boolean administrator) {this.administrator = administrator;}
    public int getExpenses() {return expenses;}
    public void setExpenses(int expenses) {this.expenses = expenses;}
    public String getMenu() {return menu;}
    public void setMenu(String menu) {this.menu = menu;}
    public int getId() {return id;}
    public void setId(int id) {this.id = id;}
}
