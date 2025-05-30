package controller;

import java.util.ArrayList;

import model.*;




public class GymController {
	
	ArrayList<Member> members;
	
	
	ArrayList<Subscription>subscriptions;
	
	public GymController()
	{
		members = new ArrayList<>();
		subscriptions = new ArrayList<>();
	}
	
	public void addMember(String name, int age, String gender, String address, String memberId,Subscription subscribe)
	{
		Member newMember = new Member(name,age,gender,address,memberId,subscribe);
		members.add(newMember);
	}
	
	public void deleteMember(String memberId)
	{
		for(var member:members)
		{
			if(member.getMemberId().equals(memberId))
			{
				members.remove(member);
			}
		}
	}
	
	public void getSubMemeberList(String subscriptionType)
	{
		ArrayList<Member> subscribers = new ArrayList<>();
		for(var member:members)
		{
			if(member.getSubscribe().equals(subscriptionType))
			{
				subscribers.add(member);
			}
		}
		System.out.println(subscribers);
	}
	
	public void getSubscriptionList()
	{
		System.out.println(subscriptions);
	}
	
	public void createSubscription()
	{
		
	}
}
