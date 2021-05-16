import com.eequalsmc2.IoTBay_Final.model.Customer;
import com.eequalsmc2.IoTBay_Final.model.dao.CustomerAccessManager;
import com.eequalsmc2.IoTBay_Final.model.dao.CustomerManager;
import com.eequalsmc2.IoTBay_Final.utils.DB;
import org.junit.*;

import static org.junit.Assert.*;

import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class CustomerTest {

    private DB db;
    private CustomerManager cm;
    private SimpleDateFormat sdf;
    private int userId;
    private String userEmail;
    private String userPassword;
    private String userFirstName;
    private String userLastName;
    private String userGender;
    private String userPhone;
    private Date userDob;

    public CustomerTest() throws SQLException, ParseException {
        db = new DB();
        cm = new CustomerManager(db);
        sdf = new SimpleDateFormat("yyyy-MM-dd");
        userEmail =  "customerTestUser@gmail.com";
        userPassword  = "testpassword";
        userFirstName  = "testUserFirstName";
        userLastName = "testUserLastName";
        userGender  = "male";
        userPhone = "123809345";
        userDob = sdf.parse("1999-07-11");
    }

    @Before
    public void setup() throws SQLException {
        this.userId = cm.create(userEmail, userFirstName, userLastName, userGender, userDob, userPhone, userPassword);
        assertTrue(this.userId != 0);
    }

    @After
    public void cleanUp() throws SQLException {
        cm.delete(userId);
        Customer user = cm.get(userId);
        assertNull(user);
    }

    @Test
    public void readFromEmailTest() throws SQLException {
        Customer retrievedUser = cm.get(userEmail);
        assertNotNull(retrievedUser);
        assertEquals(retrievedUser.getId(), userId);
        assertEquals(retrievedUser.getEmail(), userEmail);
        assertEquals(retrievedUser.getPassword(), userPassword);
        assertEquals(retrievedUser.getFirstName(), userFirstName);
        assertEquals(retrievedUser.getLastName(), userLastName);
        assertEquals(retrievedUser.getGender(), userGender);
        assertEquals(retrievedUser.getPhone(), userPhone);
        assertEquals(retrievedUser.getDob(), userDob);
    }

    @Test
    public void readFromIdTest() throws SQLException {
        Customer retrievedUser = cm.get(userId);
        assertNotNull(retrievedUser);
        assertEquals(retrievedUser.getId(), userId);
        assertEquals(retrievedUser.getEmail(), userEmail);
        assertEquals(retrievedUser.getPassword(), userPassword);
        assertEquals(retrievedUser.getFirstName(), userFirstName);
        assertEquals(retrievedUser.getLastName(), userLastName);
        assertEquals(retrievedUser.getGender(), userGender);
        assertEquals(retrievedUser.getPhone(), userPhone);
        assertEquals(retrievedUser.getDob(), userDob);
    }

    @Test
    public void updateTest() throws SQLException {
        // change user's first name and last name
        Customer customer = cm.get(userId);
        customer.setFirstName("changedFirstName");
        customer.setLastName("changedLastName");
        cm.update(customer);
        Customer retrievedUser = cm.get(userId);
        assertNotNull(retrievedUser);
        assertEquals(retrievedUser.getFirstName(), customer.getFirstName());
        assertEquals(retrievedUser.getLastName(), customer.getLastName());
    }
}
