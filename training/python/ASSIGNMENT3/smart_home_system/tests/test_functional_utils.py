import unittest
from ASSIGNMENT3.smart_home_system.manager.home_manager import HomeManager
from ASSIGNMENT3.smart_home_system.core.devices import SmartThermostat, SmartLight

class TestFunctionalUtils(unittest.TestCase):

    def setUp(self):
        self.home = HomeManager()
        self.home.add_device(SmartThermostat("T001", 23))
        self.home.add_device(SmartThermostat("T002", 22))
        self.home.add_device(SmartLight("L001", 75))
        self.home._devices[0]._SmartDevice__is_on = True
        self.home._devices[1]._SmartDevice__is_on = True
        self.home._devices[2]._SmartDevice__is_on = True

    def test_online_device_ids(self):
        online_ids = self.home.get_online_device_statuses()
        self.assertIn("T001", online_ids)

    def test_average_temperature(self):
        avg_temp = self.home.get_average_temperature()
        self.assertEqual(avg_temp, 22.5)

    def test_unique_active_types(self):
        types = self.home.Getting_unique_types_of_active_devices()
        self.assertIn("L001", types)

    def test_status_mapping(self):
        mapping = self.home.Mapping_device_IDsfull_status_reports()
        self.assertIn("T001", mapping)

    def test_generator_for_lights(self):
        lights = self.home.get_required_devices("SmartLight",True)
        self.assertNotEqual(len(lights), 0)