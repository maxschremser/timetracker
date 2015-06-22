package com.schremser.timetracker.api.util;

import com.schremser.timetracker.api.utils.DateFormatter;
import junit.framework.TestCase;
import org.apache.log4j.Logger;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by bluemax on 21.06.15.
 */
public class TestDateFormatter extends TestCase {

    public void testToday() {
        assert DateFormatter.parseToString("today").equals(new SimpleDateFormat("yyyy-MM-dd").format(new Date()));
    }

    public void testDate() {
        assert DateFormatter.parseToString("2015-05-05").equals("2015-05-05");
        assert DateFormatter.parseToString("2015-01-01").equals("2015-01-01");
        assert DateFormatter.parseToString("2015-12-31").equals("2015-12-31");
    }

    public void testNegative() {
        assertFalse(DateFormatter.parseToString("123456").equals("123456"));
        assertFalse(DateFormatter.parseToString("123456-11").equals("123456-11"));
    }
}
