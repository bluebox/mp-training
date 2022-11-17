"""converters"""
class MyConverter:
    """myconverter"""
    regex = '[a-z]{5}[0-9]{4}'

    def to_python(self, value):
        """to python"""
        return value

    def to_url(self, value):
        """to url"""
        return value
