package org.empathy.soulsplit.woodcutting;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.io.IOException;
import java.net.URL;
import java.util.Arrays;

import javax.imageio.ImageIO;

import org.parabot.core.Context;
import org.parabot.environment.api.interfaces.Paintable;
import org.parabot.environment.scripts.Category;
import org.parabot.environment.scripts.Script;
import org.parabot.environment.scripts.ScriptManifest;
import org.soulsplit.api.events.MessageEvent;
import org.soulsplit.api.events.listeners.MessageListener;
import org.soulsplit.api.methods.Skill;

@ScriptManifest(author = "Empathy", category = Category.WOODCUTTING, description = "A SoulSplit AIO Woodcutter", name = "EWoodcutter", servers = { "Soulsplit" }, version = 1.0)
public class EWoodcutter extends Script implements Paintable, MessageListener {

	public static String status = "";

	public static String mode;

	public static TreeData treeData;

	public static ScriptTimer time;

	public static boolean brokeHatchet;
	
	private Image getImage(String url) {
		try {
			return ImageIO.read(new URL(url));
		} catch (IOException e) {
			return null;
		}
	}

	private final Color color1 = new Color(255, 255, 255);

	private final Font font1 = new Font("Arial", 0, 9);

	private final Image img1 = getImage("http://i.imgur.com/3wcAicH.png");

	@Override
	public void paint(Graphics g1) {
		Graphics2D g = (Graphics2D) g1;
		g.drawImage(img1, 0, 344, null);
		g.setFont(font1);
		g.setColor(color1);
		g.drawString("" + time.toString(), 128, 435);
		g.drawString("" + time.getGainedAmount() + " (" + time.getPerHour(time.getGainedAmount()) + " / hr)", 309, 435);
		g.drawString("" + time.getXpGained(), 128, 478);
		g.drawString("" + status, 309, 478);
		g.drawString("" + mode, 128, 519);
	}

	@Override
	public boolean onExecute() {

		Context.getInstance().getRandomHandler().clearRandoms();
		Context.getInstance().getRandomHandler().clearActiveRandoms();
		System.out.println("Randoms cleared");

		Gui GUI = new Gui();

		GUI.setVisible(true);

		while (GUI.isVisible()) {
			sleep(1000);
		}
		
		time = new ScriptTimer(Skill.WOODCUTTING, treeData.getLogId());
		
		provide(Arrays.asList(new Relog(), new Banking(), new Chop(), new Drop(), new AxeAntiRandom(), new Walking()));

		return true;
	}

	@Override
	public void messageReceived(MessageEvent m) {
		if (m.getType() == 0) {
			if ((m.getMessage().contains("do not have a hatchet"))) {
				brokeHatchet = true;
			}
		}
	}
}