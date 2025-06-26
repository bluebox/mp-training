package Library.src.test.java.com.LibraryManagement;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;

// Import your service test classes
import Library.src.test.java.com.LibraryManagement.service.BookServiceImplTest;
import Library.src.test.java.com.LibraryManagement.service.MemberServiceImplTest;
import Library.src.test.java.com.LibraryManagement.service.IssueRecordServiceImplTest;

/**
 * Test Suite to run all service-related test cases together.
 */
@RunWith(Suite.class)
@Suite.SuiteClasses({
    BookServiceImplTest.class,
    MemberServiceImplTest.class,
    IssueRecordServiceImplTest.class
})
public class AppTestSuite {
    // No implementation needed, annotations take care of running tests
}


















//package Library.src.test.java.com.LibraryManagement;
//
//import junit.framework.Test;
//import junit.framework.TestCase;
//import junit.framework.TestSuite;
//
///**
// * Unit test for simple App.
// */
//public class AppTest 
//    extends TestCase
//{
//    /**
//     * Create the test case
//     *
//     * @param testName name of the test case
//     */
//    public AppTest( String testName )
//    {
//        super( testName );
//    }
//
//    /**
//     * @return the suite of tests being tested
//     */
//    public static Test suite()
//    {
//        return new TestSuite( AppTest.class );
//    }
//
//    /**
//     * Rigourous Test :-)
//     */
//    public void testApp()
//    {
//        assertTrue( true );
//    }
//}
