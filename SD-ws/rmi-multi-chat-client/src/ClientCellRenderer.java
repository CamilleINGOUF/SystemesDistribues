import java.awt.Color;
import java.awt.Component;
import java.rmi.RemoteException;

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
		} catch (RemoteException e) {
			e.printStackTrace();
		}
		
		if (isSelected) {
			setBackground(HIGHLIGHT_COLOR);
			setForeground(Color.white);
		} else {
			setBackground(Color.white);
			setForeground(Color.black);
		}
		return this;
	}

}
