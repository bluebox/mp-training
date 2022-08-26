

class RollNumber:
    regex = '[0-9]{4}'

    def to_python(self, value):
        value = 'MED' + value
        return value

    def to_url(self, value):
        return value

