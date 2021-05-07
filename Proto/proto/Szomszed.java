package proto;

import java.io.Serializable;

//
//
//  Generated by StarUML(tm) Java Add-In
//
//  @ Project : Untitled
//  @ File Name : Szomsz�d.java
//  @ Date : 2021. 04. 08.
//  @ Author : 
//
//

public abstract class Szomszed implements Serializable{
	/**
	 * A szerializ�l�shoz sz�ks�ges ID
	 */
	private static final long serialVersionUID = -7002053200057188774L;
	/**
	 * A Szomsz�d egyedi azonos�t�ja
	 */
	protected String ID;

	/**
	 * Absztrakt f�ggv�ny a Szomsz�d robban�s�ra, amit a lesz�rmazottak val�s�tanak meg
	 */
	public abstract void Robban();
	
	/**
	 * Absztrakt f�ggv�ny a Szomsz�d egyik j�t�kban l�v� Entit�s befogad�s�ra, 
	 * amit a lesz�rmazottak val�s�tanak meg
	 * @param a az Entit�s, amelyik r�ker�l az adott Szomsz�dra
	 */
	public abstract void Befogad(Entitas a);
	
	/**
	 * Absztrakt f�ggv�ny a Szomsz�d napvihar kezel�s�re, amit a lesz�rmazottak val�s�tanak meg
	 */
	public abstract void Napvihar();
	
	/**
	 * Absztrakt f�ggv�ny a Szomsz�d egyik Teleportkapu befogad�s�ra, 
	 * amit a lesz�rmazottak val�s�tanak meg
	 * @param k a Teleportkapu, amelyik r�ker�l az adott Szomsz�dra
	 */
	public abstract void KapuBefogad(Teleportkapu k);
	
	/**
	 * Absztrakt f�ggv�ny a Szomsz�d megkerg�l�s�re, amit a lesz�rmazottak val�s�tanak meg
	 */
	public  abstract void MegKergul();
	
	/**
	 * Absztrakt f�ggv�ny a Szomsz�d egyik szomsz�dj�nak t�rl�s�re, amit a lesz�rmazottak val�s�tanak meg
	 * @param a a Szomsz�d, amely m�r nem lesz t�bb� a szomsz�dja
	 */
	public  abstract void SzomszedTorol(Szomszed a);

	/**
	 * Visszaadja az adott Szomsz�d egyedi azonos�t�j�t
	 * @return a Szomsz�d egyedi azonos�t�ja
	 */
	public String getID() {
		return ID;
	}
	
	/**
	 * Be�ll�tja a szomsz�d egyedi azonos�t�j�t
	 * @param id a string, ami az egyedi azonos�t� lesz
	 */
	public void setID(String id) {
		ID = id;
	}
	public abstract boolean Kompatibilis(Szomszed sz);
}
