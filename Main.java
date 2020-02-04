
import javafx.application.Application;
import javafx.application.Platform;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.Button;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.CornerRadii;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

public class Main extends Application
{
    public static Color[] colors = {Color.BLUE, Color.PURPLE, Color.CRIMSON, Color.YELLOW, Color.GREEN};
    public static Color default_color = Color.WHITE;

    public int[][] array;

    int px = 50;
    int py = 50;
    static int x = 10;
    static int y = 10;

    int color_code = 0;
    int reset_code = -1;
    int prev_color_code = -1;
    boolean fill_mode = false;

    VBox root = new VBox();
    Button reset = new Button("RESET");
    Button mode = new Button("1 - TILE");
    Button color = new Button("COLOR");
    Canvas can = new Canvas(x * px, y * py);
    @Override
    public void start(Stage primaryStage) throws Exception
    {
        reset.setOnAction(actionEvent -> reset());
        reset.setMinWidth(x * px);
        mode.setOnAction(actionEvent -> mode());
        mode.setMinWidth(x * px);
        color.setOnAction(actionEvent -> color());
        color.setMinWidth(x * px);
        color.setBackground(new Background(new BackgroundFill(colors[color_code], CornerRadii.EMPTY, new Insets(3, 3, 3, 3))));

        root.getChildren().addAll(reset, mode, color, can);

        init();

        primaryStage.setTitle("TEST APPLICATION");
        primaryStage.setScene(new Scene(root, x * px, y * py + 75));
        primaryStage.setResizable(false);
        primaryStage.setOnCloseRequest(e -> Platform.exit());
        primaryStage.show();
    }

    public void init()
    {
        array = new int[x][y];
        for(int i = 0; i < x; i++)
        {
            for(int j = 0; j < y; j++)
            {
                array[i][j] = reset_code;
            }
        }

        can.setOnMouseClicked(event -> can(event));
        draw();
    }

    public void reset()
    {
        for(int i = 0; i < x; i++)
        {
            for(int j = 0; j < y; j++)
            {
                array[i][j] = reset_code;
            }
        }

        draw();
    }

    public void can(MouseEvent event)
    {
        int temp_x = (int) event.getX();
        int temp_y = (int) event.getY();
        int cx = temp_x / px;
        int cy = temp_y / py;

        if(!fill_mode)
        {
            array[cx][cy] = color_code;
        }
        else
        {
            prev_color_code = array[cx][cy];
            if(!(color_code == array[cx][cy]))
            {
                flood_fill(cx, cy);
            }
        }

        draw();
    }

    public void mode()
    {
        fill_mode = !fill_mode;

        if(fill_mode)
        {
            mode.setText("FLOOD FILL");
        }
        else
        {
            mode.setText("1 - TILE");
        }
    }

    public void color()
    {
        if(color_code == colors.length - 1)
        {
            color_code = 0;
        }
        else
        {
            color_code++;
        }

        color.setBackground(new Background(new BackgroundFill(colors[color_code], CornerRadii.EMPTY, new Insets(3, 3, 3, 3))));
    }

    public void draw()
    {
        GraphicsContext gc = can.getGraphicsContext2D();
        for(int i = 0; i < x; i++)
        {
            for(int j = 0; j < y; j++)
            {
                if(array[i][j] >= 0)
                {
                    gc.setFill(colors[array[i][j]]);
                }
                else
                {
                    gc.setFill(default_color);
                }
                gc.fillRect(i * px, j * py, px, py);
            }
        }
    }

    public void flood_fill(int x, int y)
    {
        if (x < 0 || x >= this.x || y < 0 || y >= this.y) return;
        if (array[x][y] != prev_color_code) return;

        array[x][y] = color_code;

        flood_fill(x+1, y);
        flood_fill(x-1, y);
        flood_fill(x, y+1);
        flood_fill(x, y-1);
    }

    public static void main(String[] args)
    {
        launch(args);
    }
}
