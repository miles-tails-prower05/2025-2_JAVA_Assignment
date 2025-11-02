// 소스파일 - https://github.com/miles-tails-prower05/2025-2_JAVA_Assignment/blob/main/src/move/ViewPanel.java

package move;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

// 뷰 패널
public class ViewPanel extends JPanel
{
	protected MazeCharacter character;
	protected Timer timer;
	protected JLabel label;

	public ViewPanel( MazeCharacter character ){
		setLayout(new BorderLayout());
		label = new JLabel();
		label.setFont( new Font( "굴림", Font.BOLD, 17 ) );
		//label.setBounds(800, 703, 500, 20);
		add(label, BorderLayout.SOUTH);
		
		// 키리스너에 방향키로 이동하는 캐릭터를 등록
		this.character = character;
		addKeyListener( character );
		setFocusable( true );
		requestFocus();
		setPreferredSize( new Dimension( character.backgroundWidth(), character.backgroundHeight() ) );

		// 주기적으로 활성화되는 타이머 등록
		timer = new Timer( 100, new ActionListener() {
		                	@Override
		                	public void actionPerformed( ActionEvent event ) {
		                		update();
		                		repaint();
		                	}
		                });
		timer.start();
	}

	// 최신 정보 업데이트
	protected void update() {
		character.move();
		label.setText("주변에 " + character.searchMine() + "개의 지뢰가 있습니다.");
	}

	// 화면 출력
	@Override
	public void paint( Graphics g ){
		super.paint( g );
		character.paint( g );
	}
}

