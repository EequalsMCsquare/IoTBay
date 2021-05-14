import com.eequalsmc2.IoTBay_Final.model.Customer;
import com.eequalsmc2.IoTBay_Final.model.Staff;
import com.eequalsmc2.IoTBay_Final.model.dao.StaffManager;
import com.eequalsmc2.IoTBay_Final.utils.DB;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import static org.junit.Assert.*;

public class StaffTest {
    private DB db;
    private StaffManager sm;
    private SimpleDateFormat sdf;

    private int userId;
    private String userEmail;
    private String userPassword;
    private String userFirstName;
    private String userLastName;
    private String userGender;
    private String userPhone;
    private Date userDob;
    private int userPrivilege;
    private String userPosition;

    public StaffTest() throws SQLException, ParseException {
        db = new DB();
        sm = new StaffManager(db);
        sdf = new SimpleDateFormat("yyyy-MM-dd");

        userEmail =  "testuser-xiao@staff.iotbay.com";
        userPassword  = "testpassword";
        userFirstName  = "testUserFirstName";
        userLastName = "testUserLastName";
        userGender  = "male";
        userPhone = "123123123";
        userDob = sdf.parse("1999-07-11");
        userPrivilege = 1;
        userPosition = "salesperson";
    }

    @Before
    public void setup() throws SQLException {
        this.userId = sm.create(userEmail, userFirstName, userLastName, userGender, userDob, userPhone, userPassword,userPrivilege, userPosition);
        assertNotEquals(0, this.userId);
    }

    @After
    public void cleanUp() throws SQLException {
        sm.delete(userId);
        Staff user = sm.get(userId);
        assertNull(user);
    }

    @Test
    public void readFromEmailTest() throws SQLException {
        Staff retrievedUser = sm.get(userEmail);
        assertNotNull(retrievedUser);
        assertEquals(retrievedUser.getId(), userId);
        assertEquals(retrievedUser.getEmail(), userEmail);
        assertEquals(retrievedUser.getPassword(), userPassword);
        assertEquals(retrievedUser.getFirstName(), userFirstName);
        assertEquals(retrievedUser.getLastName(), userLastName);
        assertEquals(retrievedUser.getGender(), userGender);
        assertEquals(retrievedUser.getPhone(), userPhone);
        assertEquals(retrievedUser.getDob(), userDob);
        assertEquals(retrievedUser.getPrivilege(), userPrivilege);
        assertEquals(retrievedUser.getPosition(), userPosition);
    }

    @Test
    public void readFromIdTest() throws SQLException {
        Staff retrievedUser = sm.get(userId);
        assertNotNull(retrievedUser);
        assertEquals(retrievedUser.getId(), userId);
        assertEquals(retrievedUser.getEmail(), userEmail);
        assertEquals(retrievedUser.getPassword(), userPassword);
        assertEquals(retrievedUser.getFirstName(), userFirstName);
        assertEquals(retrievedUser.getLastName(), userLastName);
        assertEquals(retrievedUser.getGender(), userGender);
        assertEquals(retrievedUser.getPhone(), userPhone);
        assertEquals(retrievedUser.getDob(), userDob);
        assertEquals(retrievedUser.getPrivilege(), userPrivilege);
        assertEquals(retrievedUser.getPosition(), userPosition);
    }

    @Test
    public void updateTest() throws SQLException {
        // change user's first name and last name
        Staff staff = sm.get(userId);
        staff.setFirstName("changedFirstName");
        staff.setLastName("changedLastName");
        sm.update(staff);
        Staff retrievedUser = sm.get(userId);
        assertNotNull(retrievedUser);
        assertEquals(retrievedUser.getFirstName(), staff.getFirstName());
        assertEquals(retrievedUser.getLastName(), staff.getLastName());
    }

    @Test
    public void getAllTest() throws SQLException {
        List<Staff> staff = sm.getAll();
        assertNotNull(staff);
        assertTrue(staff.size() > 0);
    }
}
