import java.util.List;
import org.sql2o.*;

public class Client {
  private int id;
  private String client_name;
  private int stylistId;

  public Client(String client_name, int stylistId) {
    this.client_name = client_name;
    this.stylistId = stylistId;
  }
//Getter methods
  public String getClientName() {
    return client_name;
  }

  public int getId() {
    return id;
    //how are we getting id?
  }

  public int getStylistId() {
    return stylistId;
    //how does the server know to populate the Client table:stylistId column with the contents of Stylists table:id column? App.java?
  }

  public static List<Client> all() {
    //'list', as opposed to 'arraylist' is generic so no need for property or constructor?
    String sql = "SELECT id, client_name, stylistId FROM clients";
    try(Connection con = DB.sql2o.open()) {
      return con.createQuery(sql).executeAndFetch(Client.class);
      //What does .class do exactly??
    }
  }

  @Override
  public boolean equals(Object otherClient) {
    if (!(otherClient instanceof Client)) {
      return false;
    } else {
      Client newClient = (Client) otherClient;
      return this.getClientName().equals(newClient.getClientName()) &&
        this.getId() == newClient.getId();
        //not sure how these last three lines affect firstClient vs secondClient equality
    }
  }

  public void save() {
  try(Connection con = DB.sql2o.open()) {
    String sql = "INSERT INTO clients(client_name, stylistId) VALUES (:client_name, :stylistId)";
    this.id = (int) con.createQuery(sql, true)
      .addParameter("client_name", this.client_name)
      .addParameter("stylistId", this.stylistId)
      //where did we "add the int stylistId parameter to our save() method" (https://www.learnhowtoprogram.com/java/java-database-basics/to-do-list-with-db-backed-one-to-many)
        .executeUpdate()
        .getKey();
    }
  }

  public static Client find(int id) {
    try(Connection con = DB.sql2o.open()) {
      String sql = "SELECT * FROM clients where id=:id";
      Client client = con.createQuery(sql)
        .addParameter("id", id)
        .executeAndFetchFirst(Client.class);
      return client;
    }
  }

  public void update(String client_name) {
    try(Connection con = DB.sql2o.open()) {
      String sql = "UPDATE clients SET client_name = :client_name WHERE id = :id";
      con.createQuery(sql)
        .addParameter("client_name", client_name)
        .addParameter("id", id)
        .executeUpdate();
    }
  }

  public void delete() {
    try(Connection con = DB.sql2o.open()) {
    String sql = "DELETE FROM clients WHERE id = :id;";
    con.createQuery(sql)
      .addParameter("id", id)
      .executeUpdate();
    }
  }
}
