import java.awt.Color;
import java.awt.Component;
import java.awt.Image;
import java.rmi.RemoteException;

import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.ListCellRenderer;

import common.ClientInterface;

public class ClientCellRenderer extends JLabel implements ListCellRenderer<ClientInterface> {


	private static final Color HIGHLIGHT_COLOR = new Color(0, 0, 128);

	public ClientCellRenderer() 
	{
		setOpaque(true);
		setIconTextGap(12);
	}

	@Override
	public Component getListCellRendererComponent(JList<? extends ClientInterface> list, ClientInterface value, int index, boolean isSelected,
			boolean cellHasFocus) 
	{
		try {
			setText(value.giveYourName());
			Image image = value.getYourAvatar().getImage(); // transform it 
			Image newimg = image.getScaledInstance(50, 50,  java.awt.Image.SCALE_SMOOTH); // scale it the smooth way  
			ImageIcon img = new ImageIcon(newimg);
			setIcon(img);
		} catch (RemoteException e) {
			e.printStackTrace();
		}
		
		if (isSelected) {
			setBackground(HIGHLIGHT_COLOR);
			setForeground(Color.WHITE);
		} else {
			setBackground(Color.white);try {
				setForeground(value.giveColor());
			} catch (RemoteException e) {
				e.printStackTrace();
			}
		}
		return this;
	}

}
