import functions
import pytest
class TestFunctions:
    def test_circle(self):
        assert functions.circle(7) == 3.14 * 7 ** 2
    def test_square(self):
        assert functions.square(10) == 10**2
    def test_rectangle(self):
        assert functions.rectangle(10,20) == 10*20
    def test_triangle(self):
        assert functions.triangle(10,5) == 25
    def test_parallelogram(self):
        assert functions.parallelogram(10,20) == 200
    # def test_



