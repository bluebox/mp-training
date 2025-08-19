import asyncio


from  ASSIGNMENT3.smart_home_system.manager.home_manager import HomeManager
from  ASSIGNMENT3.smart_home_system.manager.scene_manager import *
from  ASSIGNMENT3.smart_home_system.manager.scheduler import *
from ASSIGNMENT3.smart_home_system.core.devices import *
from ASSIGNMENT3.smart_home_system.core.security import *
from  ASSIGNMENT3.smart_home_system.utils.helpers import SmartHomeJSONEncoder

async def main():
    # operations on individual devices
    #SmartLight
    light1=SmartLight("L101",40,True)
    print("from @property light brightness=",light1.brightness)
    # light1.brightness=(int(input("enter the new brightness:")),"pRasad")
    print("after setting brightness ::",light1.brightness)
    print("the following are the available operation :",light1.get_supported_actions())
    await light1.perform_action("set brightness","pRasad",96)
    await light1.perform_action("Turn On","pRasad")
    await light1.perform_action("Turn off", "pRasad")
    await light1.perform_action("get status report", "pRasad")
    print("-"*30)
    # #thermostat
    thermostat1 = SmartThermostat("T201", 23.5)
    print("from @property thermostat temperature=", thermostat1.temperature)
    # thermostat1.temperature=(float(input("enter the new temperature value:")),"pRasad")
    print("after setting temperature ::", thermostat1.temperature)
    print("the following are the available operation :", thermostat1.get_supported_actions())
    await thermostat1.perform_action("set temperature", "pRasad", 96)
    await thermostat1.perform_action("Turn On", "pRasad")
    await thermostat1.perform_action("Turn off", "pRasad")
    await thermostat1.perform_action("get status report", "pRasad")
    print("-" * 30)
    # #DoorLock
    doorLock1 = SmartDoorLock("D301", "Prasad123#", True)
    print("from @property DoorLock passcode=", doorLock1.passcode)
    # doorLock1.passcode =((input("enter the new passcode :")), "pRasad")
    # print("after setting passcode ::", doorLock1.passcode)
    print("the following are the available operation :", doorLock1.get_supported_actions())
    await doorLock1.perform_action("Lock Door", "pRasad")
    await doorLock1.perform_action("Un Lock Door", "pRasad","Prasad123#")
    await doorLock1.perform_action("Un Lock Door", "adoin","Prasad123#")
    print(await doorLock1.perform_action("get status report", "pRasad"))
    await doorLock1.perform_action("set passcode", "pRasad", "Abcd@123#")
    print("-" * 30)

    #camera
    camera1 = SmartCamera("C401", 6)
    print("from @property camera resolution=", camera1.resolution)
    # camera1.resolution=(int(input("enter the new resolution:")),"pRasad")
    print("after setting camera resolution ::", camera1.resolution)
    print("the following are the available operation :", camera1.get_supported_actions())
    # ["set resolution", "get status report", "Turn ff", "Turn on"]
    await camera1.perform_action("set resolution", "pRasad", 96)
    await camera1.perform_action("Turn On", "pRasad")
    await camera1.perform_action("Turn off", "pRasad")
    print(await camera1.perform_action("get status report", "pRasad"))
    print("-" * 30)
    # #Speaker
    #
    speaker1 = SmartSpeaker("S501", 30)
    print("from @property speaker Volume=", speaker1.volume)
    # speaker1.volume=(int(input("enter the new Volume:")),"pRasad")
    print("after setting Volume ::", speaker1.volume)
    print("the following are the available operation :", speaker1.get_supported_actions())
    # [ "set volume","get_status_report","play","Turn OFF", "Turn ON"]

    await speaker1.perform_action("set volume", "pRasad", 98)
    await speaker1.perform_action("Turn On", "pRasad")
    await speaker1.perform_action("Turn off", "pRasad")
    print(await speaker1.perform_action("get status report", "pRasad"))
    await speaker1.perform_action("play", "pRasad")
    print("-" * 30)

    light2 = SmartLight("L102", 30, False)
    light3 = SmartLight("L103", 101, True)
    light4 = SmartLight("L104", 70, True)

    thermostat2=SmartThermostat("T202",32.5)
    thermostat3 = SmartThermostat("T203", 33.5)
    thermostat4= SmartThermostat("T204", 25.05)

    doorLock2 = SmartDoorLock("D302", "Welcme!23##",True)
    doorLock3 = SmartDoorLock("D303", "Haha123@",False)
    doorLock4 = SmartDoorLock("D304", "pRASAD123#",True)

    camera2 = SmartCamera("C402",7)
    camera3 = SmartCamera("C403",12.36,False)
    camera4 = SmartCamera("C404",4.5)

    speaker2= SmartSpeaker("S502", 10)
    speaker3 = SmartSpeaker("S503", 110)
    speaker4 = SmartSpeaker("S504", 70)

    alaram1 = SmartAlarmSystem("A601", True)
    print(alaram1.get_status_report())
    alaram1.schedule_task("01:22", "get status report", 45)
    print(alaram1.get_supported_actions())
    # ['arm', 'disarm', 'get status report', 'schedule task']
    await alaram1.turn_on("pRasad")
    await alaram1.turn_off()
    await alaram1.turn_on("pRasad")
    await alaram1.perform_action("arm", "pRasad")
    await  alaram1.perform_action("disarm")
    await alaram1.perform_action("schedule task", "pRasad", 78, "09:55")
    print(alaram1.get_status_report())



    home = HomeManager()
    home.add_device(light1)
    home.add_device(light2)
    home.add_device(light3)
    home.add_device(light4)
    # print(home.get_online_device_statuses())

    home.add_device(thermostat1)
    home.add_device(thermostat2)
    home.add_device(thermostat3)
    home.add_device(thermostat4)

    home.add_device(doorLock1)
    home.add_device(doorLock2)
    home.add_device(doorLock3)
    home.add_device(doorLock4)

    home.add_device(camera1)
    home.add_device(camera2)
    home.add_device(camera3)
    home.add_device(camera4)

    home.add_device(speaker1)
    home.add_device(speaker2)
    home.add_device(speaker3)
    home.add_device(speaker4)
    # await home.turn_off_all()
    await home.turn_on_all("pRasad")

    SceneManager
    scene_manager = SceneManager()
    scene_manager.add_scene("morning",("L101","set brightness",66),("L102","set brightness",22))
    scene_manager.add_scene("evening", ("L102", "turn on",45,),("L102", "turn on",45))
    scene_manager.add_scene("Fire", ("A101", "arm", 66))
    print("-"*30)
    await scene_manager.activate_scene(home,"morning","pRasad")
    await scene_manager.activate_scene(home,"evening","pRasad")

    # await home.turn_on_all("pRasad")
    print("no error upto here")
    #Scheduler
    scheduler = Scheduler()
    scheduler.add_scheduled_task("11:19","C402","set resolution",12,"pRasad")
    scheduler.add_scheduled_task("11:17", "L103", "set brightness", 12, "pRasad")
    scheduler.add_scheduled_task("10:50", "L101", "turn off", 12, "ahsgd")
    scheduler.add_scheduled_task("10:45", "L104", "turn on", 12, "pRasad")
    await  scheduler.run_scheduled_tasks(home)
    await  scheduler.run_pending_schedule(home)

    smartHomeJSONEncoder=SmartHomeJSONEncoder()


    smartHomeJSONEncoder.smart_devices_to_json(home,"encoded.json")
    devices=smartHomeJSONEncoder.smart_devices_from_json("encoded.json")
    print(devices)
    print(len(devices))


    home.get_all_device_statuses()

asyncio.run(main())
