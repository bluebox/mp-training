from example_package.random_int import solve
import unittest

class TestSolve(unittest.TestCase):
    def test_solve(self):
        result=solve(10,10)
        self.assertTrue(result)
    def test_solve2(self):
        result=solve(10,'sljfs')
        self.assertRaises(TypeError)

if __name__=='__main__':
    unittest.main()