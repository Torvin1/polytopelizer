package input;

import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;



// 
class InputFrameListener extends WindowAdapter
{
  public void windowClosing(WindowEvent e)
  {
    e.getWindow().dispose();
    System.exit(0);
    }
}