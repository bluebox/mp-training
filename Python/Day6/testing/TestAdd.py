from .. import add
import unittest

class TestAdd(unittest.TestCase):
    def setUp(self):  # this is like initiate like DBConnection etc
        self.ans=[10,20,30]
    def test_add(self):
        print(self.ans[0])
        result=add.add(10,20)
        self.assertEqual(result,30)
    def test_add2(self):
        with self.assertRaises(TypeError):
            result=add.add(10,"sldjf")
    def tearDown(self):# to close the initiation
        self.ans.clear()
if __name__=='__main__':
    unittest.main()