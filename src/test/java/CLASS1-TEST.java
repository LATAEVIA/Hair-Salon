import org.sql2o.*;
import org.junit.*;
import static org.junit.Assert.*;
// import java.util.Arrays;

public class CLASS1 {

  @Before
  public void setUp() {
    DB.sql2o = new Sql2o("jdbc:postgresql://localhost:5432/(DATABASE-NAME)", null, null);
  }

  @After
  public void tearDown() {
    try(Connection con = DB.sql2o.open()) {

    }
  }
}
