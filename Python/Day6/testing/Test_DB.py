from DBConnection import add_employee,get_employee
import unittest

class TestDB(unittest.TestCase):
    def test_add_employee(self):
        result=add_employee(22,'anand','43000')
        self.assertTrue(result)
    def test_get_employee(self):
        result=get_employee(5)
        print(result)
        self.assertEqual(result[0][3],43000)
if __name__=='__main__':
    unittest()
