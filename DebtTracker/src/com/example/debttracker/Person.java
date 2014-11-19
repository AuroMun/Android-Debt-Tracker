package com.example.debttracker;

public class Person {
		private int id;
		private String name;
		private int money;
		public Person(){}
		public Person(int money, String nama){
			super();
			this.money=money;
			this.name=nama;
		}
		//getters and setters, apparently
		@Override
		public String toString(){
			return "Person [id=" + id +", money=" + money+", name=" + name +"]";
		}
		
		public int getMoney(){
			return money;
		}
		public String getName(){
			return name;
		}
		public int getId(){
			return id;
		}
		public void setId(int id){this.id=id;}
		public void setName(String name){this.name=name;}
		public void setMoney(int money){this.money=money;}
}
