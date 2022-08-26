class FourDigitYearConverter:
    regex = '[0-9]{4}'

    def to_python(self, value):
        temp = 5*value
        return temp

    def to_url(self, value):
        return value
