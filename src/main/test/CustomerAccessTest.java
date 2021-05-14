import com.eequalsmc2.IoTBay_Final.model.Customer;
import com.eequalsmc2.IoTBay_Final.model.UserAccess;
import com.eequalsmc2.IoTBay_Final.model.dao.CustomerAccessManager;
import com.eequalsmc2.IoTBay_Final.model.dao.CustomerManager;
import com.eequalsmc2.IoTBay_Final.utils.DB;
import org.junit.Test;

import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import static org.junit.Assert.*;

public class CustomerAccessTest {

    private DB db;
    private CustomerAccessManager cam;
    private CustomerManager cm;
    private Customer user;
    private SimpleDateFormat sdf;



    public CustomerAccessTest() throws SQLException, ParseException {
        this.db = new DB();
        this.cam = new CustomerAccessManager(db);
        this.cm = new CustomerManager(db);
        this.sdf = new SimpleDateFormat("yyyy/MM/dd");
        this.user = new Customer();
        user.setEmail("customerAccessTestUser@gmail.com");
        user.setPassword("testpassword");
        user.setFirstName("testuser");
        user.setLastName("xiao");
        user.setGender("male");
        user.setPhone("123123123");
        user.setDob(sdf.parse("1999/07/11"));
    }

    @Test
    public void createUser() throws SQLException {
        int id = cm.create(user);
        assertNotEquals(0, id);
    }

    @Test
    public void createTest() throws SQLException {
        user = cm.get(user.getEmail());
        cam.create(user.getId(), "testAccess");
        cam.create(user.getId(), "testAccess");
        cam.create(user.getId(), "testAccess");
        cam.create(user.getId(), "testAccess");
        cam.create(user.getId(), "testAccess");
    }

    @Test
    public void readTest() throws SQLException {
        user = cm.get(user.getEmail());
        ArrayList<UserAccess> accesses = cam.get(user.getId());
        assertEquals(accesses.size(), 5);
        for(UserAccess e: accesses) {
            assertEquals("testAccess", e.getType());
        }
    }

    @Test
    public void deleteTest() throws SQLException {
        user = cm.get(user.getEmail());
        ArrayList<UserAccess> accesses = cam.get(user.getId());
        for (UserAccess e: accesses) {
            cam.delete(e.getId());
        }
        accesses = cam.get(user.getId());
        assertEquals(0, accesses.size());
    }

    @Test
    public void deleteUser() throws SQLException {
        user = cm.get(user.getEmail());
        cm.delete(user.getId());
    }

//    @Test
//    public void readBetweenTest() throws SQLException, InterruptedException {
//        user = cm.get(user.getEmail());
//        Date start = new Date();
//        cam.create(user.getId(), "validAccess");
//        cam.create(user.getId(), "validAccess");
//        cam.create(user.getId(), "validAccess");
//        cam.create(user.getId(), "validAccess");
//        cam.create(user.getId(), "validAccess");
//        Thread.sleep(1000);
//        Date end = new Date();
//        cam.create(user.getId(), "invalidAccess");
//        cam.create(user.getId(), "invalidAccess");
//        cam.create(user.getId(), "invalidAccess");
//
//        ArrayList<UserAccess> accesses = cam.getBetween(user.getId(), start, end);
//        assertEquals(5,accesses.size());
//        for(UserAccess e: accesses) {
//            assertEquals(e.getType(), "validAccess");
//        }
//
//        // clean up
//        accesses = cam.get(user.getId());
//        for(UserAccess e: accesses) {
//            cam.delete(e.getId());
//        }
//    }
}
