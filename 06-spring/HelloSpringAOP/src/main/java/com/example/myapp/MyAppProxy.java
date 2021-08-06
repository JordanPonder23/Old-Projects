package com.example.myapp;

import org.springframework.stereotype.Component;

@Component("appProxy")
public class MyAppProxy {
	public int drawCartoon() {
		/*System.out.println("build enthusiasm...");*/
		System.out.println("--- drawing a cartoon! ^_^ ---");
		/*System.out.println("snack break!");*/
		return 3;
	}
	
	public int drawAnime(int a) {
		System.out.println("--- drawing anime! XD ---");
		return 360;
	}
	
	public int drawTheBestAnime(int a, int b) {
		System.out.println("--- drawing Lilo&Stitch! :P ---");
		return 720;
	}
	
	protected void drawNature() {
		/*System.out.println("build enthusiasm...");*/
		System.out.println("--- drawing some trees *-* ---");
		/*System.out.println("snack break!");*/
	}
	public void sculptPottery() {
		/*System.out.println("build enthusiasm");*/
		System.out.println("--- pot sculpting =O ---");
		/*System.out.println("take a nap, then wash hands, in that order");*/
	}
}
