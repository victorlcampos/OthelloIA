package main;

import java.awt.EventQueue;

import controller.God;

import view.Tabuleiro;

public class Main {

	
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Tabuleiro.getInstance().start();
					God.getInstance();
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}


	
}
