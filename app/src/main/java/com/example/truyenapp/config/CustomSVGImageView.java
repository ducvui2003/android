package com.example.truyenapp.config;

import android.content.Context;
import android.graphics.Canvas;
import android.util.AttributeSet;

import androidx.appcompat.widget.AppCompatImageView;

import com.caverock.androidsvg.SVG;
import com.caverock.androidsvg.SVGParseException;

import java.io.InputStream;

public class CustomSVGImageView extends AppCompatImageView {
    private SVG svg;

    public CustomSVGImageView(Context context) {
        super(context);
        init(null);
    }

    public CustomSVGImageView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init(attrs);
    }

    public CustomSVGImageView(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
        init(attrs);
    }

    private void init(AttributeSet attrs) {
        // Load SVG from attribute or resource
        if (attrs != null) {
            // Get SVG resource ID from XML attributes
            int resourceId = attrs.getAttributeResourceValue("http://schemas.android.com/apk/res-auto", "svg", 0);
            if (resourceId != 0) {
                // Load SVG from resource
                InputStream inputStream = getResources().openRawResource(resourceId);
                try {
                    svg = SVG.getFromInputStream(inputStream);
                } catch (SVGParseException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        if (svg != null) {
            // Render SVG to canvas
            svg.renderToCanvas(canvas);
        }
    }
}
