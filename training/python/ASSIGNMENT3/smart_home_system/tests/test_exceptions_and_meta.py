import unittest
from ASSIGNMENT3.smart_home_system.core.exceptions import *

class TestExceptionsAndMetaclass(unittest.TestCase):

    def test_custom_exceptions(self):
        self.assertTrue(issubclass(InvalidParameterError, SmartHomeError))
        self.assertTrue(issubclass(DeviceOfflineError, SmartHomeError))
        self.assertTrue(issubclass(PermissionDeniedError, SmartHomeError))
        self.assertTrue(issubclass(AuthenticationError, SmartHomeError))

