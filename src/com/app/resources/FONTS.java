package com.app.resources;

import java.awt.Font;

public record FONTS() {
    static final Font Title = new Font("Consolas", Font.BOLD, 40);
    static final Font Label = new Font("Monospaced", Font.PLAIN, 14);
    static final Font Input = new Font("Arial", Font.BOLD, 16);
    static final Font Button = new Font("Tahoma", Font.BOLD, 18);
    static final Font Error_msg = new Font("Verdana", Font.ITALIC, 12);
}
