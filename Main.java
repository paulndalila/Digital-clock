package com.clock;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.SwingConstants;
import java.awt.Font;
import java.awt.Image;
import java.awt.SystemTray;
import java.awt.Toolkit;
import java.awt.TrayIcon;
import java.awt.TrayIcon.MessageType;
import javax.swing.JButton;
import java.awt.AWTException;
import java.awt.Color;
import java.awt.Desktop;

import javax.swing.JTextField;
import javax.swing.border.CompoundBorder;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;
import java.net.InetSocketAddress;
import java.net.Socket;
import java.time.DayOfWeek;
import java.time.Month;
import java.time.Year;
import java.awt.event.ActionEvent;
import javax.swing.JComboBox;
import javax.swing.border.MatteBorder;
import javax.swing.border.BevelBorder;
import java.awt.SystemColor;
import javax.swing.border.TitledBorder;
import javax.swing.border.LineBorder;
import javax.swing.JSpinner;
import javax.swing.SpinnerNumberModel;
import javax.swing.JPanel;
import com.media.*;

public class Main {

	private static JButton bEdit, bBack, bAlarm, bReset, bSave,bSaveAlarm,bDiscard,bHideSeconds,bShowSeconds,bHideZero,bShowZero,b1224,bHiddenFolder_1,bHiddenFolder,bMusic,bOK;
	private static JLabel lhrs,lmins,lsecs,loader,lampm,lcl,lcll,lset2,lset,alarmLabel,lday,lcolon_1,lalarmsetat,lMusicLabel;
	private static JTextField thide;
	private static JComboBox<String> cbAMPM,cbaampm,cb1224,cbExtension;
	private static String AMorPM="AM",note=null, l1224="12",fmon,fhor,fampm,fday,theDay,storeThm="",Link="",ext="",thrs,tmins,alatext="\"Hello Paul! It's time for coding!\"";
	private static JFrame frmClock;
	private static int h=3,m=0,s=1,count=1,even,hor=0,mon=0,trayy=0,storeTh=0,storeTm=0,changeCo,bMS=1;
	private static boolean AlarmON=false,to12hr=true,ifSecOn=true,putZero=true,Playlist=false,NATGEO=false,MyLink=false;
	private static SystemTray tray;
	private static TrayIcon trayicon;
	private static JSpinner stmins,stahrs,stamins,sthrs;
	private static JPanel panel;
	private static JButton bColor,bAlarmText;
	private static JTextField tLink;
	
	public static void main(String[] args) {
		
		try {
			
			frmClock=new JFrame();
			frmClock.setLocationByPlatform(true);
			frmClock.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
			frmClock.setTitle("Clock");
			frmClock.setResizable(false);
			frmClock.getContentPane().setBackground(SystemColor.inactiveCaption);
			frmClock.getContentPane().setLayout(null);
			
			tthide();
			to12hr=true;
			setFrameTitle();
			
			lhrs = new JLabel("00");
 			lhrs.setForeground(Color.BLACK);
			lhrs.setFont(new Font("Tahoma", Font.PLAIN, 30));
			lhrs.setHorizontalAlignment(SwingConstants.CENTER);
			lhrs.setBounds(68, 57, 59, 89);
			frmClock.getContentPane().add(lhrs);
			
			JLabel lcolon = new JLabel(":");
			lcolon.setForeground(Color.BLACK);
			lcolon.setFont(new Font("Tahoma", Font.PLAIN, 24));
			lcolon.setHorizontalAlignment(SwingConstants.CENTER);
			lcolon.setBounds(137, 57, 20, 89);
			frmClock.getContentPane().add(lcolon);
			
			 lampm = new JLabel(AMorPM);
			 lampm.setForeground(Color.BLACK);
			lampm.setFont(new Font("Sitka Subheading", Font.BOLD, 15));
			lampm.setBounds(329, 95, 50, 35);
			frmClock.getContentPane().add(lampm);
			
			 lmins = new JLabel("00");
			 lmins.setForeground(Color.BLACK);
			lmins.setHorizontalAlignment(SwingConstants.CENTER);
			lmins.setFont(new Font("Tahoma", Font.PLAIN, 30));
			lmins.setBounds(167, 57, 59, 89);
			frmClock.getContentPane().add(lmins);
			
			lsecs = new JLabel("00");
			lsecs.setForeground(Color.BLACK);
			lsecs.setHorizontalAlignment(SwingConstants.CENTER);
			lsecs.setFont(new Font("Tahoma", Font.PLAIN, 30));
			lsecs.setBounds(266, 57, 59, 89);
			frmClock.getContentPane().add(lsecs);
			
			lcolon_1 = new JLabel(":");
			lcolon_1.setForeground(Color.BLACK);
			lcolon_1.setHorizontalAlignment(SwingConstants.CENTER);
			lcolon_1.setFont(new Font("Tahoma", Font.PLAIN, 24));
			lcolon_1.setBounds(236, 57, 20, 89);
			frmClock.getContentPane().add(lcolon_1);
			
			 bBack = new JButton("Back");
			 bBack.setFont(new Font("Dialog", Font.PLAIN, 11));
			 bBack.setForeground(Color.BLACK);
			 bBack.setBorder(new LineBorder(Color.DARK_GRAY));
			 bBack.setToolTipText("");
			 bBack.addActionListener(new ActionListener() {
			 	public void actionPerformed(ActionEvent e) {

			 		closeEdits();
			 		closeAlarm();
			 	}
			 });
			bBack.setBackground(Color.LIGHT_GRAY);
			bBack.setBounds(410, 187, 89, 20);
			frmClock.getContentPane().add(bBack);
			
			putZero=true;
			
			 bEdit = new JButton("Options");
			 bEdit.setFont(new Font("Dubai Medium", Font.BOLD, 12));
			 bEdit.addActionListener(new ActionListener() {
			 	public void actionPerformed(ActionEvent e) {

			 		showEdits();
			 		
			 		if(putZero) {
			 			bShowZero.setVisible(false);
			 			bHideZero.setVisible(true);
			 		}else {
			 			bShowZero.setVisible(true);
			 			bHideZero.setVisible(false);
			 		}
			 	}
			 });
			bEdit.setForeground(Color.BLACK);
			bEdit.setBackground(Color.GRAY);
			bEdit.setBounds(410, 101, 89, 20);
			frmClock.getContentPane().add(bEdit);
			
			 bReset = new JButton("Reset");
			 bReset.setFont(new Font("Dubai Medium", Font.BOLD, 12));
			 bReset.addActionListener(new ActionListener() {
			 	public void actionPerformed(ActionEvent e) {
			 		
			 		reset();
			 		
			 	}
			 });
			bReset.setForeground(Color.BLACK);
			bReset.setBackground(Color.GRAY);
			bReset.setBounds(410, 70, 89, 20);
			frmClock.getContentPane().add(bReset);
			
			 bAlarm = new JButton("Alarm");
			 bAlarm.setFont(new Font("Dubai Medium", Font.BOLD, 12));
			 bAlarm.addActionListener(new ActionListener() {
			 	public void actionPerformed(ActionEvent e) {
			 		
			 		setAlarm();
			 	}
			 });
			bAlarm.setForeground(Color.BLACK);
			bAlarm.setBackground(Color.GRAY);
			bAlarm.setBounds(410, 39, 89, 20);
			frmClock.getContentPane().add(bAlarm);
			
			 cbAMPM = new JComboBox<String>();
			 cbAMPM.setBorder(new MatteBorder(1, 1, 1, 1, (Color) new Color(102, 0, 102)));
			cbAMPM.setFont(new Font("Tahoma", Font.PLAIN, 11));
			cbAMPM.setForeground(Color.BLACK);
			cbAMPM.setBackground(Color.LIGHT_GRAY);
			cbAMPM.addItem("AM");
			cbAMPM.addItem("PM");
			cbAMPM.setBounds(236, 157, 50, 17);
			frmClock.getContentPane().add(cbAMPM);
			
			lcl = new JLabel(":");
			lcl.setHorizontalAlignment(SwingConstants.CENTER);
			lcl.setFont(new Font("Tahoma", Font.PLAIN, 24));
			lcl.setBounds(137, 157, 20, 35);
			frmClock.getContentPane().add(lcl);
			
			bSave = new JButton("Save time");
			bSave.setFont(new Font("Dialog", Font.PLAIN, 11));
			bSave.setToolTipText("Save time set");
			bSave.setBorder(new LineBorder(Color.DARK_GRAY));
			bSave.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					
					saveTime();
					closeEdits();
					
				}
			});
			bSave.setForeground(Color.BLACK);
			bSave.setBackground(Color.LIGHT_GRAY);
			bSave.setBounds(91, 187, 112, 20);
			frmClock.getContentPane().add(bSave);
			
			cbaampm = new JComboBox<String>();
			cbaampm.setBorder(new MatteBorder(1, 1, 1, 1, (Color) new Color(102, 0, 102)));
			cbaampm.setFont(new Font("Tahoma", Font.PLAIN, 11));
			cbaampm.setForeground(Color.BLACK);
			cbaampm.setBackground(Color.LIGHT_GRAY);
			cbaampm.addItem("AM");
			cbaampm.addItem("PM");
			cbaampm.setBounds(236, 157, 50, 20);
			frmClock.getContentPane().add(cbaampm);
			
			bSaveAlarm = new JButton("Save");
			bSaveAlarm.setFont(new Font("Dialog", Font.PLAIN, 11));
			bSaveAlarm.setToolTipText("Save new alarm");
			bSaveAlarm.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					saveAlarm();
					bDiscard.setVisible(true);
				}
			});
			bSaveAlarm.setBorder(new LineBorder(Color.DARK_GRAY));
			bSaveAlarm.setForeground(Color.BLACK);
			bSaveAlarm.setBackground(Color.LIGHT_GRAY);
			bSaveAlarm.setBounds(91, 187, 112, 20);
			frmClock.getContentPane().add(bSaveAlarm);
			
			lcll = new JLabel(":");
			lcll.setFont(new Font("Tahoma", Font.BOLD, 15));
			lcll.setHorizontalAlignment(SwingConstants.CENTER);
			lcll.setBounds(137, 157, 20, 20);
			frmClock.getContentPane().add(lcll);
			
			JLabel lblNewLabel = new JLabel("contact:paulndalila001@gmail.com");
			lblNewLabel.setVerticalAlignment(SwingConstants.BOTTOM);
			lblNewLabel.setHorizontalAlignment(SwingConstants.RIGHT);
			lblNewLabel.setFont(new Font("Dialog", Font.PLAIN, 9));
			lblNewLabel.setBounds(300, 208, 199, 12);
			frmClock.getContentPane().add(lblNewLabel);
			
			JButton bUpdate = new JButton("Update");
			bUpdate.setFont(new Font("Dubai Medium", Font.BOLD, 12));
			bUpdate.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					
					updateTime();
				}
			});
			bUpdate.setForeground(Color.BLACK);
			bUpdate.setBackground(Color.GRAY);
			bUpdate.setBounds(410, 132, 89, 20);
			frmClock.getContentPane().add(bUpdate);
			
			lday = new JLabel("New label");
			lday.setForeground(Color.BLACK);
			lday.setFont(new Font("Times New Roman", Font.BOLD, 12));
		    lday.setHorizontalAlignment(SwingConstants.CENTER);
			lday.setBounds(401, 0, 112, 33);
			frmClock.getContentPane().add(lday);
			
			 lset = new JLabel("Set:");
			lset.setVerticalAlignment(SwingConstants.BOTTOM);
			lset.setFont(new Font("Tahoma", Font.BOLD, 12));
			lset.setHorizontalAlignment(SwingConstants.RIGHT);
			lset.setBounds(12, 157, 50, 14);
			frmClock.getContentPane().add(lset);
			
			 lset2 = new JLabel("Set:");
			lset2.setVerticalAlignment(SwingConstants.BOTTOM);
			lset2.setHorizontalAlignment(SwingConstants.RIGHT);
			lset2.setFont(new Font("Tahoma", Font.BOLD, 12));
			lset2.setBounds(8, 157, 54, 20);
			frmClock.getContentPane().add(lset2);
			
			note="::: Alarm set at: 00 : 00 AM";
			
			JLabel ltitle = new JLabel("Ndalila clock");
			ltitle.setVerticalAlignment(SwingConstants.TOP);
			ltitle.setForeground(Color.BLACK);
			ltitle.setHorizontalAlignment(SwingConstants.LEFT);
			ltitle.setFont(new Font("Snap ITC", Font.PLAIN, 17));
			ltitle.setBounds(42, 11, 169, 35);
			frmClock.getContentPane().add(ltitle);
						
			cb1224 = new JComboBox<String>();
			cb1224.setBackground(SystemColor.inactiveCaption);
			cb1224.setBorder(new TitledBorder(new LineBorder(new Color(0, 0, 0)), "12 \\24hr", TitledBorder.LEADING, TitledBorder.TOP, null, new Color(0, 0, 0)));
			cb1224.setBounds(322, 139, 68, 45);
			cb1224.addItem("12");
			cb1224.addItem("24");
			cb1224.setSelectedItem(l1224);
			cb1224.setForeground(Color.BLACK);
			frmClock.getContentPane().add(cb1224);
			
			bHideSeconds = new JButton("Hide seconds");
			bHideSeconds.setBorder(new LineBorder(new Color(0, 0, 0), 1, true));
			bHideSeconds.setFont(new Font("Dubai Medium", Font.PLAIN, 11));
			bHideSeconds.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					hideSeconds();
				}
			});
			bHideSeconds.setForeground(Color.BLACK);
			bHideSeconds.setBackground(Color.LIGHT_GRAY);
			bHideSeconds.setBounds(52, 50, 112, 23);
			frmClock.getContentPane().add(bHideSeconds);
			
			bShowSeconds = new JButton("Show seconds");
			bShowSeconds.setBorder(new LineBorder(new Color(0, 0, 0), 1, true));
			bShowSeconds.setFont(new Font("Dubai Medium", Font.PLAIN, 11));
			bShowSeconds.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					showSeconds();
				}
			});
			bShowSeconds.setForeground(Color.BLACK);
			bShowSeconds.setBackground(Color.LIGHT_GRAY);
			bShowSeconds.setBounds(52, 50, 112, 23);
			frmClock.getContentPane().add(bShowSeconds);
			
			bHideZero = new JButton("Hide zero");
			bHideZero.setBorder(new LineBorder(new Color(0, 0, 0), 1, true));
			bHideZero.setFont(new Font("Dubai Medium", Font.PLAIN, 11));
			bHideZero.setForeground(Color.BLACK);
			bHideZero.setBackground(Color.LIGHT_GRAY);
			bHideZero.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					    putZero=false;
						removeZero();
						bShowZero.setVisible(true);
						bHideZero.setVisible(false);
				}
			});
			bHideZero.setBounds(266, 50, 112, 23);
			frmClock.getContentPane().add(bHideZero);
			
			bShowZero = new JButton("Show zero");
			bShowZero.setBorder(new LineBorder(new Color(0, 0, 0), 1, true));
			bShowZero.setFont(new Font("Dubai Medium", Font.PLAIN, 11));
			bShowZero.setForeground(Color.BLACK);
			bShowZero.setBackground(Color.LIGHT_GRAY);
			bShowZero.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					    putZero=true;
						removeZero();
						bShowZero.setVisible(false);
						bHideZero.setVisible(true);
				}
			});
			bShowZero.setBounds(266, 50, 112, 23);
			frmClock.getContentPane().add(bShowZero);
			
			lalarmsetat = new JLabel(note);
			lalarmsetat.setForeground(new Color(102, 0, 51));
			lalarmsetat.setBounds(76, 41, 229, 40);
			frmClock.getContentPane().add(lalarmsetat);
			
			sthrs = new JSpinner();
			sthrs.setBorder(new MatteBorder(1, 1, 1, 1, (Color) new Color(102, 0, 102)));
			sthrs.setModel(new SpinnerNumberModel(0, 0, 23, 1));
			sthrs.setBounds(68, 157, 68, 20);
			frmClock.getContentPane().add(sthrs);
			
			stmins = new JSpinner();
			stmins.setBorder(new MatteBorder(1, 1, 1, 1, (Color) new Color(102, 0, 102)));
			stmins.setModel(new SpinnerNumberModel(0, 0, 59, 1));
			stmins.setBounds(158, 157, 68, 20);
			frmClock.getContentPane().add(stmins);
			
			stahrs = new JSpinner();
			stahrs.setBorder(new MatteBorder(1, 1, 1, 1, (Color) new Color(102, 0, 102)));
			stahrs.setModel(new SpinnerNumberModel(0, 0, 23, 1));
			stahrs.setBounds(68, 157, 68, 20);
			frmClock.getContentPane().add(stahrs);
			
			stamins = new JSpinner();
			stamins.setBorder(new MatteBorder(1, 1, 1, 1, (Color) new Color(102, 0, 102)));
			stamins.setModel(new SpinnerNumberModel(0, 0, 59, 1));
			stamins.setBounds(158, 157, 68, 20);
			frmClock.getContentPane().add(stamins);
			
			if1224();
			
			b1224 = new JButton("Save");
			b1224.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					String getComboClick=(String) cb1224.getSelectedItem();
					cb1224.setSelectedItem(getComboClick);
					
					if(getComboClick.equals("24")) {
						to24hr();
						if1224();
						cbAMPM.setVisible(false);
					}else {
						to12hr();
						if1224();
						if(to12hr) {
							cbAMPM.setVisible(true);
						}
					}
				}
			});
			b1224.setBorder(new LineBorder(Color.DARK_GRAY));
			b1224.setForeground(Color.BLACK);
			b1224.setBackground(Color.LIGHT_GRAY);
			b1224.setFont(new Font("Dialog", Font.PLAIN, 11));
			b1224.setBounds(322, 187, 68, 20);
			frmClock.getContentPane().add(b1224);
			
			panel = new JPanel();
			panel.setBackground(Color.BLACK);
			panel.setForeground(Color.BLACK);
			panel.setBounds(302, 126, 3, 81);
			frmClock.getContentPane().add(panel);
			
			changeCo=2;
			
			bColor = new JButton("Color");
			bColor.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					changeColor();
					changeCo++;
				
				}
			});
			bColor.setBackground(Color.CYAN);
			bColor.setBounds(410, 158, 89, 23);
			frmClock.getContentPane().add(bColor);
			
			loader = new JLabel(".");
			loader.setFont(new Font("Tahoma", Font.BOLD, 15));
			loader.setHorizontalAlignment(SwingConstants.CENTER);
			loader.setForeground(Color.BLACK);
			loader.setBounds(404, 19, 99, 18);
			frmClock.getContentPane().add(loader);
			
			bAlarmText = new JButton("Change alarm text?");
			bAlarmText.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					alatext=JOptionPane.showInputDialog(frmClock, "Enter new alarm text:");
					alarmLabel.setText(alatext);
				}
			});
			bAlarmText.setForeground(Color.BLACK);
			bAlarmText.setFont(new Font("Dubai Medium", Font.PLAIN, 11));
			bAlarmText.setBorder(new LineBorder(Color.BLACK));
			bAlarmText.setBackground(Color.LIGHT_GRAY);
			bAlarmText.setBounds(68, 126, 218, 20);
			frmClock.getContentPane().add(bAlarmText);
			
			tLink = new JTextField();
			tLink.setFont(new Font("Segoe Script", Font.BOLD, 9));
			tLink.setBorder(new MatteBorder(1, 1, 1, 1, (Color) SystemColor.activeCaption));
			tLink.setBounds(181, 32, 89, 14);
			frmClock.getContentPane().add(tLink);
			tLink.setColumns(10);
			
			cbExtension = new JComboBox<String>();
			cbExtension.setFont(new Font("Segoe Script", Font.BOLD, 9));
			cbExtension.setBorder(new CompoundBorder());
			cbExtension.addItem("mp3");
			cbExtension.addItem("mp4");
			cbExtension.addItem("M4A");
			cbExtension.addItem("3gp");
			cbExtension.addItem("wav");
			cbExtension.setBounds(276, 32, 47, 14);
			frmClock.getContentPane().add(cbExtension);
			
			bOK = new JButton("Ok");
			bOK.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					String titleLink=tLink.getText();
					ext=(String) cbExtension.getSelectedItem();
					Link="C:\\Users\\DELL\\Desktop\\"+titleLink+"."+ext;
					lMusicLabel.setText(titleLink.substring(26));
					closeLink();
				}
			});
			bOK.setFont(new Font("Segoe Script", Font.BOLD, 9));
			bOK.setBounds(329, 32, 64, 14);
			frmClock.getContentPane().add(bOK);
			
			alarmLabel = new JLabel(alatext);
			alarmLabel.setVerticalAlignment(SwingConstants.BOTTOM);
			alarmLabel.setHorizontalAlignment(SwingConstants.CENTER);
			alarmLabel.setFont(new Font("Dubai", Font.PLAIN, 20));
			alarmLabel.setBounds(26, 31, 374, 40);
			frmClock.getContentPane().add(alarmLabel);
			
			alarmLabel.setVisible(false);
			lalarmsetat.setVisible(false);
			
			frmClock.setBounds(3, 7, 529, 263);
			frmClock.setVisible(true);
			frmClock.setBackground(SystemColor.inactiveCaption);	
			ifSecOnOff();
			closeEdits();
			closeAlarm();
			updateTime();
			dayOfWeek();

			for(;s<=61;s++) {

				load();
				
				if(s==60) {
					s=0;
					m++;
					if(m==60) {
						m=0;
						h++;
						
						if(to12hr) {
							if(h==12) {
								count++;
								ampm();
								if(AlarmON&&AMorPM.equals("AM")) {
									fday="Today";
									note="::: Alarm set at: "+fhor+" : "+fmon+" "+fampm+" : "+fday;
									lalarmsetat.setText(note);
								}
							}
							if(h==13) {
								h=1;
							}
							dayOfWeek();
							setFrameTitle();
						}else {
							
							if(h==24) {
								h=0;
								if(AlarmON) {
									fday="Today";
									note="::: Alarm set at: "+fhor+" : "+fmon+" "+fampm+" : "+fday;
									lalarmsetat.setText(note);
								}
							}
							dayOfWeek();
							setFrameTitle();
						}
					}
					
				}
				Thread.sleep(1000);
				formatTime();
				ring12();
			}
			
		}catch(Exception e) {
			System.out.println(e);
		}
	}
	public static void showLink() {
		cbExtension.setVisible(true);
		tLink.setVisible(true);
		bOK.setVisible(true);
	}public static void closeLink() {
		cbExtension.setVisible(false);
		tLink.setVisible(false);
		bOK.setVisible(false);
	}
	private static void tthide() {
		
			bDiscard = new JButton("Discard");
			bDiscard.setToolTipText("Stop alarm");
			bDiscard.setBorder(new BevelBorder(BevelBorder.RAISED, Color.PINK, null, null, null));
			bDiscard.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					AlarmON=false;
					try {
					if(trayy==3433) {
						tray.remove(trayicon);	
					}
					closeRing();
					bSaveAlarm.setText("Save Alarm");
					note="::: Alarm set at: 00 : 00 AM";
					lalarmsetat.setText(note);
				}catch(Exception ee) {
					System.out.println(ee);
				}
				}
			});

			bMusic = new JButton(" || ::");
			bMusic.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					bMS++;
					if(bMS==2) {
						closeLink();
						bMusic.setText(" |> ::");
						bMusic.setBackground(new Color(0, 153, 51));
						lMusicLabel.setText("Alarm will start your music playlist.");
						Playlist=true;
						MyLink=false;
						
					}else if(bMS==4) {
						bMusic.setText(" |> ::");
						bMusic.setBackground(new Color(0, 153, 51));
						lMusicLabel.setText("Copy media link here: ");
						showLink();
						MyLink=true;
						Playlist=false;
						bMS=0;
				    }else if(bMS==1){
				    	closeLink();
						bMusic.setText("|| ::");
						bMusic.setBackground(Color.WHITE);
						lMusicLabel.setText("Click '||' to set an event when Alarm is ON");
						Playlist=false;
						MyLink=false;
					}
				}
			});
			
			lMusicLabel = new JLabel("Click '||' to set an event when Alarm is ON");
			lMusicLabel.setForeground(Color.BLACK);
			lMusicLabel.setFont(new Font("Segoe Script", Font.BOLD, 10));
			lMusicLabel.setBounds(57, 32, 229, 14);
			frmClock.getContentPane().add(lMusicLabel);

			bMusic.setForeground(new Color(0, 0, 51));
			bMusic.setBorder(new BevelBorder(BevelBorder.RAISED, null, null, null, null));
			bMusic.setBackground(Color.WHITE);
			bMusic.setBounds(26, 31, 28, 15);
			frmClock.getContentPane().add(bMusic);
            
			bDiscard.setForeground(Color.WHITE);
			bDiscard.setBackground(Color.DARK_GRAY);
			bDiscard.setFont(new Font("Tahoma", Font.BOLD, 11));
			bDiscard.setBounds(266, 14, 112, 23);
			frmClock.getContentPane().add(bDiscard);
			bDiscard.setVisible(false);

		thide = new JTextField();
		thide.setEditable(false);
		thide.setBorder(new CompoundBorder());
		thide.setBackground(SystemColor.inactiveCaption);
		thide.setBounds(221, 0, 169, 20);
		frmClock.getContentPane().add(thide);
		thide.setColumns(10);
	}
	private static void formatTime() {
		//seconds formatting
		if(s==60) {
			lsecs.setText("00");

		}else if(s<=9) {
			lsecs.setText("0"+s);

		}else {
			lsecs.setText(""+s);

		}
		
		//minutes formatting
		if(m==60) {
			lmins.setText("00");

		}else if(m<=9) {
			lmins.setText("0"+m);

		}else {
			lmins.setText(""+m);

		}
		
		//hours formatting
		if(h<=12 && h>=10) {
			lhrs.setText(""+h);

		}else if(h<=9) {
			lhrs.setText("0"+h);

		}
		
		//12 or 24 am pm formatting
		if(to12hr) {
			lampm.setText(AMorPM);
		}else {
			lampm.setText("hrs");
		}
		
		//zero removal
		removeZero();
	}
	public static void load() {
		
		
		if(s<=1 || s==7|| s==13|| s==19|| s==25|| s==31|| s==37|| s==43|| s==49|| s==55) {
			loader.setText(".");
		}else if(s<=2|| s==8|| s==14|| s==20|| s==26|| s==32|| s==38|| s==44|| s==50|| s==56) {
			loader.setText("..");
		}else if(s<=3|| s==9|| s==15|| s==21|| s==27|| s==33|| s==39|| s==45|| s==51|| s==57) {
			loader.setText("...");
		}else if(s<=4|| s==10|| s==16|| s==22|| s==28|| s==34|| s==40|| s==46|| s==52|| s==58) {
			loader.setText("......");
		}else if(s<=5|| s==11|| s==17|| s==23|| s==29|| s==35|| s==41|| s==47|| s==53|| s==59) {
			loader.setText(".........");
		}else if(s<=6|| s==12|| s==18|| s==24|| s==30|| s==36|| s==42|| s==48|| s==54|| s==60||s==0) {
			loader.setText("..........");
		}
	}
	private static void changeColor() {
		if(changeCo==1) {
			frmClock.getContentPane().setBackground(SystemColor.inactiveCaption);
			bHiddenFolder.setBackground(SystemColor.inactiveCaption);
			bHiddenFolder_1.setBackground(SystemColor.inactiveCaption);
			bColor.setBackground(Color.CYAN);
		}else if(changeCo==2) {
			frmClock.getContentPane().setBackground(Color.CYAN);
			bHiddenFolder.setBackground(Color.CYAN);
			bHiddenFolder_1.setBackground(Color.CYAN);
			bColor.setBackground(Color.BLUE);
		}else if(changeCo==3) {
			frmClock.getContentPane().setBackground(Color.BLUE);
			bHiddenFolder.setBackground(Color.BLUE);
			bHiddenFolder_1.setBackground(Color.BLUE);
			bColor.setBackground(Color.DARK_GRAY);
		}else if(changeCo==4) {
			frmClock.getContentPane().setBackground(Color.DARK_GRAY);
			bHiddenFolder.setBackground(Color.DARK_GRAY);
			bHiddenFolder_1.setBackground(Color.DARK_GRAY);
			alarmLabel.setForeground(Color.WHITE);
			bColor.setBackground(Color.GREEN);
		}else if(changeCo==5) {
			frmClock.getContentPane().setBackground(Color.GREEN);
			bHiddenFolder.setBackground(Color.GREEN);
			bHiddenFolder_1.setBackground(Color.GREEN);
			bColor.setBackground(Color.MAGENTA);
		}else if(changeCo==6) {
			frmClock.getContentPane().setBackground(Color.MAGENTA);
			bHiddenFolder.setBackground(Color.MAGENTA);
			bHiddenFolder_1.setBackground(Color.MAGENTA);
			alarmLabel.setForeground(Color.WHITE);
			bColor.setBackground(Color.ORANGE);
		}else if(changeCo==7) {
			frmClock.getContentPane().setBackground(Color.ORANGE);
			bHiddenFolder.setBackground(Color.ORANGE);
			bHiddenFolder_1.setBackground(Color.ORANGE);
			alarmLabel.setForeground(Color.BLACK);
			bColor.setBackground(Color.PINK);
		}else if(changeCo==8) {
			frmClock.getContentPane().setBackground(Color.PINK);
			bHiddenFolder.setBackground(Color.PINK);
			bHiddenFolder_1.setBackground(Color.PINK);
			bColor.setBackground(Color.LIGHT_GRAY);
		}else if(changeCo==9) {
			frmClock.getContentPane().setBackground(Color.LIGHT_GRAY);
			bHiddenFolder.setBackground(Color.LIGHT_GRAY);
			bHiddenFolder_1.setBackground(Color.LIGHT_GRAY);
			bColor.setBackground(Color.WHITE);
		}else if(changeCo==10) {
			frmClock.getContentPane().setBackground(Color.WHITE);
			bHiddenFolder.setBackground(Color.WHITE);
			bHiddenFolder_1.setBackground(Color.WHITE);
			bColor.setBackground(Color.RED);
		}else if(changeCo==11) {
			frmClock.getContentPane().setBackground(Color.RED);
			bHiddenFolder.setBackground(Color.RED);
			bHiddenFolder_1.setBackground(Color.RED);
			alarmLabel.setForeground(Color.YELLOW);
			bColor.setBackground(Color.YELLOW);
		}else if(changeCo==12) {
			frmClock.getContentPane().setBackground(Color.YELLOW);
			bHiddenFolder.setBackground(Color.YELLOW);
			bHiddenFolder_1.setBackground(Color.YELLOW);
			alarmLabel.setForeground(Color.BLACK);
			bColor.setBackground(SystemColor.inactiveCaption);
		}else {
			changeCo=1;
			frmClock.getContentPane().setBackground(SystemColor.inactiveCaption);
			bHiddenFolder.setBackground(SystemColor.inactiveCaption);
			bHiddenFolder_1.setBackground(SystemColor.inactiveCaption);
			bColor.setBackground(Color.CYAN);
		}
		thide.setVisible(false);
	}
	private static void saveTime() {
		
		thrs=(String.valueOf(sthrs.getAccessibleContext().getAccessibleValue().getCurrentAccessibleValue().intValue()));
		tmins=(String.valueOf(stmins.getAccessibleContext().getAccessibleValue().getCurrentAccessibleValue().intValue()));
		
		if(to12hr) {
			if(thrs.equals("") || tmins.equals("") ||thrs.equals("") && tmins.equals("") || thrs.equals(null)&& tmins.equals(null)) {
				
				int o=m;
				int o1=h;
				int o2=s;
				
				h=o1;
				m=o;
				s=o2;
				
			}else {
				String hours=thrs;
				String minutes=tmins;

				int mn=Integer.parseInt(minutes);
				int hr=Integer.parseInt(hours);
				
				String tampm=(String) cbAMPM.getSelectedItem();
				cbAMPM.setSelectedItem(tampm);
				
				h=hr;
				m=mn;
				AMorPM=tampm;
				formatTime();
				if(tampm.equals("PM")) {
					count=2;
				}else {
					count=1;
				}
			}
		}else {
			if(thrs.equals("") || tmins.equals("") ||thrs.equals("") && tmins.equals("") || thrs.equals(null)&& tmins.equals(null)) {
				
				int o=m;
				int o1=h;
				int o2=s;
				
				h=o1;
				m=o;
				s=o2;
				
			}else {
				String hours=thrs;
				String minutes=tmins;

				int mn=Integer.parseInt(minutes);
				int hr=Integer.parseInt(hours);
				
				h=hr;
				m=mn;
			}
		}
		
	}
	private static void showEdits() {
		closeAlarm();
		if(to12hr) {
			cbAMPM.setVisible(true);
		}else {
			cbAMPM.setVisible(false);
		}
		bSave.setVisible(true);
		sthrs.setVisible(true);
		stmins.setVisible(true);
		b1224.setVisible(true);
		panel.setVisible(true);
		bColor.setVisible(true);
		lcl.setVisible(true);
		bBack.setVisible(true);
		lset.setVisible(true);
		cb1224.setVisible(true);
		ifSecOnOff();

	}
	private static void closeEdits() {
		sthrs.setVisible(false);
		bAlarmText.setVisible(false);
		bColor.setVisible(false);
		stmins.setVisible(false);
		panel.setVisible(false);
		sthrs.setValue(0);
		stmins.setValue(0);
		b1224.setVisible(false);
		cbAMPM.setVisible(false);
		bSave.setVisible(false);
		lcl.setVisible(false);
		bBack.setVisible(false);
		lset.setVisible(false);
		cb1224.setVisible(false);
		bShowSeconds.setVisible(false);
		bHideSeconds.setVisible(false);
		bShowZero.setVisible(false);
		bHideZero.setVisible(false);
	}
	private static void reset() {
    
		trayy=0;
		bSaveAlarm.setText("Save Alarm");
		note="::: Alarm set at: 00 : 00 AM";
		lalarmsetat.setText(note);
		hor=0;
		mon=0;
		
		stahrs.setToolTipText(null);
		stamins.setToolTipText(null);
	
		alarmLabel.setVisible(false);
		bAlarm.setBackground(Color.GRAY);
		bDiscard.setVisible(false);
		h=12;
		m=0;
		s=0;
		AMorPM="AM";
		formatTime();
	}
	private static void setAlarm() {
		closeEdits();
		showAlarm();
		
		lalarmsetat.setVisible(true);
		if(AlarmON) {
			bDiscard.setVisible(true);
		}
		bSaveAlarm.setText("Save Alarm");
	}
	private static void closeAlarm() {
		lcll.setVisible(false);
		bMusic.setVisible(false);
		lMusicLabel.setVisible(false);
		bAlarmText.setVisible(false);
		closeLink();
		stahrs.setValue(0);
		stamins.setValue(0);
		stahrs.setVisible(false);
		stamins.setVisible(false);
		bSaveAlarm.setVisible(false);
		if(to12hr) {
			cbaampm.setVisible(false);
		}else {
			cbaampm.setVisible(false);
		}
		bBack.setVisible(false);
		lset2.setVisible(false);
		bDiscard.setVisible(false);
		lalarmsetat.setVisible(false);

	}
	private static void showAlarm() {
		lcll.setVisible(true);
		bMusic.setVisible(true);
		lMusicLabel.setVisible(true);
		stahrs.setVisible(true);
		bAlarmText.setVisible(true);
		stamins.setVisible(true);
		bSaveAlarm.setVisible(true);
		if(to12hr) {
			cbaampm.setVisible(true);
		}else {
			cbaampm.setVisible(false);
		}
		bBack.setVisible(true);
		lset2.setVisible(true);

	}
	private static void updateTime() {
		if(!to12hr) {
			to12hr=true;
			int uhr=java.time.LocalTime.now().getHour();
			int umin=java.time.LocalTime.now().getMinute();
			int usec=java.time.LocalTime.now().getSecond();

			if(uhr>=13) {
				h=(uhr-12);
				AMorPM="PM";
			}else if(uhr==12){
				h=12;
				AMorPM="PM";
			}else if(uhr==0){
				h=12;
				AMorPM="AM";
			}else {
				h=uhr;
				AMorPM="AM";
			}

			m=umin;
			s=usec;
			
			formatTime();
			to24hr();
		}else {
			int uhr=java.time.LocalTime.now().getHour();
			int umin=java.time.LocalTime.now().getMinute();
			int usec=java.time.LocalTime.now().getSecond();

			if(uhr>=13) {
				h=(uhr-12);
				AMorPM="PM";
			}else if(uhr==12){
				h=12;
				AMorPM="PM";
			}else if(uhr==0){
				h=12;
				AMorPM="AM";
			}else {
				h=uhr;
				AMorPM="AM";
			}

			m=umin;
			s=usec;
			
			formatTime();
		}
	}
	private static void ampm() {
		even=count%2;
		if(even!=0) {
			AMorPM="AM";
		}else {
			AMorPM="PM";
		}
		
	}
	private static void saveAlarm() {
		String gtHrs=(String.valueOf(stahrs.getAccessibleContext().getAccessibleValue().getCurrentAccessibleValue().intValue()));
		String gtMins=(String.valueOf(stamins.getAccessibleContext().getAccessibleValue().getCurrentAccessibleValue().intValue()));
		
		String gtampm=(String) cbaampm.getSelectedItem();
		cbaampm.setSelectedItem(gtampm);
		
		int hh=Integer.parseInt(gtHrs);
		int mm=Integer.parseInt(gtMins);
		
		storeTh=hh;
		storeTm=mm;
		storeThm=gtampm;
		 
		 bAlarm.setBackground(Color.GREEN);
		 
		 note= "::Alarm set at: 00 : 00 AM";
		 fmon="00";
		 fhor="00";
		 fampm="AM";
		 fday="";
			
			if(to12hr) {
				//hours formatting
				if(hh<=12 && hh>=10) {
					fhor=(""+hh+"");

				}else if(hh<=9) {
					fhor=("0"+hh+"");

				}
				
				//minutes formatting
				if(mm==60) {
					fmon="00";

				}else if(mm<=9) {
					fmon=("0"+mm);

				}else if(mm>=10 && mm<=59){
					fmon=(""+mm+"");

				}
				
				fampm=gtampm;
				
				tt(hh,mm,gtampm);
			}else {
				//hours formatting
				if(hh<=24 && hh>=10) {
					fhor=(""+hh+"");

				}else if(hh<=9) {
					fhor=("0"+hh+"");

				}
				
				//minutes formatting
				if(mm==60) {
					fmon="00";

				}else if(mm<=9) {
					fmon=("0"+mm);

				}else if(mm>=10 && mm<=59){
					fmon=(""+mm+"");

				}
				//hours formatting
				if(hh<=12 && hh>=10) {
					fhor=(""+hh+"");

				}else if(hh<=9) {
					fhor=("0"+hh+"");

				}
				
				//minutes formatting
				if(mm==60) {
					fmon="00";

				}else if(mm<=9) {
					fmon=("0"+mm);

				}else if(mm>=10 && mm<=59){
					fmon=(""+mm+"");

				}
				
				fampm="hrs";
				tt(hh,mm);
			}
			
			note="::: Alarm set at: "+fhor+" : "+fmon+" "+fampm+" : "+fday;
			lalarmsetat.setText(note);
			bSaveAlarm.setText("Saved");
			trayy=0;
			AlarmON=true;
		 
	}
	public static void tt(int hh,int mm, String gtampm) {
		//math for day
		if(AMorPM.equals("AM")&&gtampm.equals("PM")) {
			fday="Today";
		}else if(AMorPM.equals("PM")&&gtampm.equals("AM")) {
			fday="Tomorrow";
		}else if(AMorPM.equals("AM")&&gtampm.equals("AM")&&h==12) {
			fday="Today";
		}else if(AMorPM.equals("AM")&&gtampm.equals("AM")&&hh>=h&&mm>m) {
			fday="Today";
		}
		else if(AMorPM.equals("AM")&&gtampm.equals("AM")&&hh>=h&&mm<m) {
			fday="Tomorrow";
		}
		else if(AMorPM.equals("AM")&&gtampm.equals("AM")&&hh<=h) {
			fday="Tomorrow";
		}else if(AMorPM.equals("PM")&&gtampm.equals("PM")&&h==12) {
			fday="Today";
		}
		else if(AMorPM.equals("PM")&&gtampm.equals("PM")&&hh>h) {
			fday="Today";
		}
		else if(AMorPM.equals("PM")&&gtampm.equals("PM")&&hh>=h&&mm>m) {
			fday="Today";
		}else if(AMorPM.equals("PM")&&gtampm.equals("PM")&&hh>=h&&mm<m) {
			fday="Tomorrow";
		}
		else if(AMorPM.equals("PM")&&gtampm.equals("PM")&&hh<=h) {
			fday="Tomorrow";
		}else {
			fday="...";
		}
	}
	public static void tt(int hh,int mm) {
		//math for day
		if(hh<h) {
			fday="Tomorrow";
		}else if(hh>h) {
			fday="Today";
		}else if(hh>=h&&mm>m) {
			fday="Today";
		}else if(hh>=h&&mm<m) {
			fday="Tomorrow";
		}
	}
	private static void ring12() throws AWTException {
		
		if(hor==h && mon==m && storeThm.equals(AMorPM) && s==0 || storeTh==h && storeTm==m && storeThm.equals(AMorPM) && s==0 || storeTh==h&&storeTm==m&&to12hr==false&&s==0 || hor==h&&mon==m&&to12hr==false&&s==0) {
			alarmLabel.setVisible(true);
			bAlarm.setBackground(Color.ORANGE);
			bDiscard.setVisible(true);
			lalarmsetat.setVisible(false);
			trayy=3433;
			Main m=new Main();
			m.tra();
			Desktop d=Desktop.getDesktop();
			if(Playlist==true) {
				try{
					d.open(new File("C:\\Users\\DELL\\Desktop\\Instrumental\\EHRLING__Sax_House_Music_Mix_2021__Deep_House_Sax_2021__Saxophone(128k).M4A"));
				}catch(IOException ioe) {
					System.out.println(ioe);
				}
			}else if(NATGEO==true) {
				try {
					d.open(new File("C:\\Users\\DELL\\Desktop\\Youtube\\Our_Planet__Coastal_Seas__FULL_EPISODE__Netflix(720p).mp4"));
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}else if(MyLink==true) {
				try {
					d.open(new File(Link));
				} catch (IOException e) {
					alarmLabel.setText("Copied media file link not found!");
				}
			}

		}
		if(h==0&&m==0&&s==0) {
			bDiscard.doClick();
		}
		
	}
	private static void closeRing() {
		
		trayy=0;
		
		hor=0;
		mon=0;
		
		stahrs.setToolTipText(null);
		stamins.setToolTipText(null);
	
		alarmLabel.setVisible(false);
		bAlarm.setBackground(Color.GRAY);
		bDiscard.setVisible(false);
		}
	private static void dayOfWeek() {
		DayOfWeek day=java.time.LocalDate.now().getDayOfWeek();
		int dayint=java.time.LocalDate.now().getDayOfMonth();
		 theDay=day.toString();
		String dayweek=toToggleCase(theDay);
		String dd=(":: "+dayint+" ' "+dayweek+" ::");
		lday.setText(dd);
	}
	private void tra() throws AWTException {
		
		String mmo="00";
		
		if(m==60) {
			mmo="00";

		}else if(m<=9) {
			mmo="0"+m;

		}else {
			mmo=""+m;

		}
		
		if(to12hr==false) {
			AMorPM="hrs";
		}
				
		String timeA="It's "+h+":"+mmo+" "+AMorPM.toLowerCase();
		try {
		tray=SystemTray.getSystemTray();
		
		Image image= Toolkit.getDefaultToolkit().createImage("Gsearch.png");
		
		trayicon=new TrayIcon(image, "Alarm");
		trayicon.setToolTip("Alarm notification");
		tray.add(trayicon);
		
		trayicon.displayMessage(timeA+", "+toToggleCase(theDay)+"", ""+alatext+"", MessageType.INFO);
		}catch(Exception e) {
			System.out.println(e);
		}
	}
	private static String toToggleCase(String s) {
		
		String fir=s.substring(0,1);
		String rest=s.substring(1);
		
		String newStr=fir+""+rest.toLowerCase();
		
		return newStr;
		
	}
	private static void to24hr() {
		if(to12hr==true) {
			to12hr=false;
			if(AMorPM.equals("PM")) {
				if(h==12) {
					h=12;
				}else {
					int yyy=h;
					h=yyy+12;
				}
			}else if(AMorPM.equals("AM")) {
				if(h==12) {
					h=0;
				}else {
					int oo=h;
					h=oo;
				}
			}
			
			//format 24hr time
			if(h<=23 && h>=10) {
				lhrs.setText(""+h);

			}else if(h<=9) {
				lhrs.setText("0"+h);

			}
			
			//
			removeZero();
			
			//set combo to 24hr
			l1224="24";
			cb1224.setSelectedItem(l1224);
			
			//if second is disabled
			if(ifSecOn==false){
				lcolon_1.setVisible(true);
				if(to12hr) {
					lcolon_1.setText(AMorPM);
				}else {
					lcolon_1.setText("hrs");
				}
			}
		}
	}
	private static void to12hr() {
		if(to12hr==false) {
			
			if(h>=13) {
				h=(h-12);
				AMorPM="PM";
			}else if(h==12){
				h=12;
				AMorPM="PM";
			}else if(h==24 || h==0){
				h=12;
				AMorPM="AM";
			}else {
				int oo=h;
				h=oo;
				AMorPM="AM";
			}
			to12hr=true;
			//format 12hr time
			if(h<=12 && h>=10) {
				lhrs.setText(""+h);

			}else if(h<=9) {
				lhrs.setText("0"+h);

			}
			//
			removeZero();
			//set combo to 12hr
			l1224="12";
			cb1224.setSelectedItem(l1224);
			//show am or pm
			lampm.setText(AMorPM);
			}
	}
	private static void hideSeconds() {
		lcolon_1.setVisible(false);
		lsecs.setVisible(false);
		ifSecOn=false;
		ifSecOnOff();
	}
	private static void showSeconds() {
		lcolon_1.setVisible(true);
		lcolon_1.setText(":");
		lcolon_1.setHorizontalAlignment(SwingConstants.CENTER);
		lcolon_1.setFont(new Font("Tahoma", Font.PLAIN, 24));
		lcolon_1.setBounds(236, 57, 20, 89);
		lsecs.setVisible(true);
		ifSecOn=true;
		ifSecOnOff();
	}
	private static void ifSecOnOff() {
		
		if(ifSecOn) {
			if(to12hr) {
				lampm.setText(AMorPM);
			}else {
				lampm.setText("hrs");
			}
			bShowSeconds.setVisible(false);
			bHideSeconds.setVisible(true);
			lampm.setVisible(true);

		}else if(ifSecOn==false){
			lcolon_1.setVisible(true);
			if(to12hr) {
				lcolon_1.setText(AMorPM);
			}else {
				lcolon_1.setText("hrs");
			}
			lcolon_1.setFont(new Font("Sitka Subheading", Font.BOLD, 15));
			lcolon_1.setHorizontalAlignment(SwingConstants.LEADING);
			lcolon_1.setBounds(225, 95, 50, 35);
			lampm.setVisible(false);
			bHideSeconds.setVisible(false);
			bShowSeconds.setVisible(true);

		}
	}
	private static void hideZero() {
		
		if(h<=9) {
			lhrs.setText("    "+h);
		}else {
			lhrs.setText("   "+h);
		}
	}
	private static void removeZero() {
		if(putZero==false) {
			hideZero();
		}else if(putZero && to12hr==true) {
			if(h<=12 && h>=10) {
				lhrs.setText(""+h);

			}else if(h<=9) {
				lhrs.setText("0"+h);

			}
		}else if(putZero && to12hr==false) {
			if(h<=24 && h>=10) {
				lhrs.setText(""+h);

			}else if(h<=9) {
				lhrs.setText("0"+h);

			}
		}
	}
	public static void ampmCombo1224() {
		if(to12hr) {
			cbAMPM.setVisible(true);
			cbaampm.setVisible(true);
		}else {
			cbAMPM.setVisible(false);
			cbaampm.setVisible(false);
		}
	}
	private static void if1224() {
		if(to12hr) {
			stahrs.setModel(new SpinnerNumberModel(0, 0, 12, 1));
			sthrs.setModel(new SpinnerNumberModel(0, 0, 12, 1));
		}else {
			stahrs.setModel(new SpinnerNumberModel(0, 0, 23, 1));
			sthrs.setModel(new SpinnerNumberModel(0, 0, 23, 1));
		}
	}
	@SuppressWarnings("deprecation")
	private static void setFrameTitle() {
		Year year=java.time.Year.now();
		
		Month month=java.time.LocalDate.now().getMonth();
		frmClock.setTitle(year+"::"+toToggleCase(month.toString()));
		frmClock.show();
		
	}
}


















