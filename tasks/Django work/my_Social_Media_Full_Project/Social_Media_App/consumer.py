from asyncio import events
import json
from time import sleep
from channels.consumer import AsyncConsumer,SyncConsumer
from channels.exceptions  import StopConsumer
from time import sleep
from channels.db import database_sync_to_async
from django.contrib.auth.models import User

import asyncio
from asgiref.sync import async_to_sync
from django.contrib.auth import authenticate
from .models import Groups, Message
class Myasync(AsyncConsumer):
    async def websocket_connect(self, event):
        print("websocket is connecting", event)
        print("channel layer is ..", self.channel_layer)
        print("channel name  is ..", self.channel_name)
        await self.send({
            'type': "websocket.accept",
        })

    async def websocket_receive(self, event):
        print("websocket is recieving....",event)
        print("message is ", event["text"])
        print("message type is ",type( event["text"]))
        for i in range(10):
            await self.send({
            'type': "websocket.send",
            "text": json.dumps({"count":i}) 
            })
            # await asyncio.sleep(1)
    

    async def websocket_disconnect(self, event):
        print("websocket is disconnecting", event)
        raise StopConsumer()



class Mysync1(SyncConsumer):
    def websocket_connect(self, event):
        print("websocket is connecting", event)
        print("channel layer is ..", self.channel_layer)
        print("channel name  is ..", self.channel_name)
        
       
        group_name = self.scope['url_route']['kwargs']['grp']
        print("scope of group name is",group_name)
        self.send({
            'type': "websocket.accept",
        })
        async_to_sync(self.channel_layer.group_add)(
            group_name,
            self.channel_name
        )
        

    def websocket_receive(self, event):
        print("websocket is recieving....",event)
        print("message is ", event["text"])
        data = event['text']

        print("message type is ",type( data))
        
        data1 = json.loads(data)

        print(" After that is message type is ",type( data1))
       
        group_name = self.scope['url_route']['kwargs']['grp']
        group = Groups.objects.get(name = group_name)
       
        
        chating = Message(
                message=data1['msg'],
                group=group
                )
        chating.save()    
        
        print("scope of group name is",group_name)
        async_to_sync( self.channel_layer.group_send)(
            group_name,{
                'type':'chat.message',
                'message':data
            }
        )

        

    def chat_message(self,event):
        print("event.....", event)
        print("event.... message is ", event['message'])
        print("event.... message data type is  ", type(event['message']))
        self.send(
            {
                'type':"websocket.send",
                 'text': event['message']
            }
        )
       

    def websocket_disconnect(self, event):
        print("websocket is disconnecting", event)
        print("channel layer is ..", self.channel_layer)
        print("channel name  is ..", self.channel_name)
        async_to_sync(self.channel_layer.group_discard)(
            'programmer',
            self.channel_name
        )
        raise StopConsumer()
















