package net.ciyuan.vanillaSkyblock.Utils;

import net.md_5.bungee.api.ChatColor;

import java.awt.*;

public class ColorUtil
{
    public static String Gradient(String text, double frequency, String... hexColors)
    {
        if (hexColors.length < 2)
        {
            throw new IllegalArgumentException("需要至少两个颜色来生成渐变");
        }
        StringBuilder sb = new StringBuilder();
        int textLength = text.length();
        int colorLength = hexColors.length;
        for (int i = 0; i < textLength; i++)
        {
            double ratio = (double) i / (textLength - 1);
            int fromColorIndex = (int) Math.floor(ratio * (colorLength - 1));
            int toColorIndex = (int) Math.ceil(ratio * (colorLength - 1));
            Color fromColor = Color.decode(hexColors[fromColorIndex]);
            Color toColor = Color.decode(hexColors[toColorIndex]);
            double localRatio = (ratio * (colorLength - 1)) - fromColorIndex;
            int red = (int) ((1 - localRatio) * fromColor.getRed() + localRatio * toColor.getRed());
            int green = (int) ((1 - localRatio) * fromColor.getGreen() + localRatio * toColor.getGreen());
            int blue = (int) ((1 - localRatio) * fromColor.getBlue() + localRatio * toColor.getBlue());
            ChatColor currentColor = ChatColor.of(new Color(red, green, blue));
            sb.append(currentColor).append(text.charAt(i));
        }
        return sb.toString();
    }
}

