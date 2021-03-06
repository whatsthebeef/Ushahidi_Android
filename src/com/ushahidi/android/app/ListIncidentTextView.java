/** 
 ** Copyright (c) 2010 Ushahidi Inc
 ** All rights reserved
 ** Contact: team@ushahidi.com
 ** Website: http://www.ushahidi.com
 ** 
 ** GNU Lesser General Public License Usage
 ** This file may be used under the terms of the GNU Lesser
 ** General Public License version 3 as published by the Free Software
 ** Foundation and appearing in the file LICENSE.LGPL included in the
 ** packaging of this file. Please review the following information to
 ** ensure the GNU Lesser General Public License version 3 requirements
 ** will be met: http://www.gnu.org/licenses/lgpl.html.	
 **	
 **
 ** If you have questions regarding the use of this file, please contact
 ** Ushahidi developers at team@ushahidi.com.
 ** 
 **/

package com.ushahidi.android.app;

import android.content.Context;
import android.graphics.Color;
import android.graphics.Typeface;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.text.Editable;
import android.text.InputType;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.text.method.KeyListener;
import android.view.KeyEvent;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;

public class ListIncidentTextView extends LinearLayout {
    private TextView title;

    private TextView iLocation;

    private TextView date;

    private TextView status;
    
    private TextView mCategories;

    private ImageView thumbnail;

    private ImageView arrow;

    private String description;

    private String categories;

    private String media;

    private String location;

    private int id;

    private float fontSize = 13.5f;

    private LinearLayout textLayout;

    private TableLayout tblLayout;

    private TableRow tblRow;

    public ListIncidentTextView(Context context, ListIncidentText listText) {
        super(context);

        this.setOrientation(VERTICAL);
        this.initComponent(context, listText);

    }

    public void initComponent(Context context, ListIncidentText listText) {
        this.textLayout = new LinearLayout(context);

        this.tblLayout = new TableLayout(context);

        this.tblLayout.setLayoutParams(new TableLayout.LayoutParams(
                android.view.ViewGroup.LayoutParams.FILL_PARENT,
                android.view.ViewGroup.LayoutParams.WRAP_CONTENT));
        this.tblLayout.setColumnStretchable(1, true);
        this.tblRow = new TableRow(context);
        this.tblRow.setLayoutParams(new TableRow.LayoutParams(
                android.view.ViewGroup.LayoutParams.FILL_PARENT,
                android.view.ViewGroup.LayoutParams.WRAP_CONTENT));

        textLayout.setOrientation(VERTICAL);
        textLayout.setPadding(0, 2, 0, 2);

        this.textLayout.setLayoutParams(new TableRow.LayoutParams(
                android.view.ViewGroup.LayoutParams.FILL_PARENT,
                android.view.ViewGroup.LayoutParams.WRAP_CONTENT));

        thumbnail = new ImageView(context);
        thumbnail.setImageURI(listText.getThumbnailUri());
        thumbnail.setImageDrawable(listText.getThumbnail());
        thumbnail.setPadding(2, 0, 10, 4);
        thumbnail.setLayoutParams(new TableRow.LayoutParams(87,
                android.view.ViewGroup.LayoutParams.FILL_PARENT));

        tblRow.addView(thumbnail);
        
        
        
        title = new TextView(context);
        
        title.setTextColor(Color.BLACK);
        title.setTextSize(fontSize);
        
        title.setSingleLine(true);
        title.setEllipsize(TextUtils.TruncateAt.END);
        title.setTypeface(Typeface.DEFAULT_BOLD);
        title.setPadding(0, 0, 2, 2);
        title.setText(listText.getTitle());
        title.setLayoutParams(new TableRow.LayoutParams(
                android.view.ViewGroup.LayoutParams.WRAP_CONTENT,
                android.view.ViewGroup.LayoutParams.WRAP_CONTENT));

        textLayout.addView(title, new TableRow.LayoutParams(
                android.view.ViewGroup.LayoutParams.FILL_PARENT,
                android.view.ViewGroup.LayoutParams.WRAP_CONTENT));

        date = new TextView(context);
        date.setTextColor(Color.GRAY);
        date.setLayoutParams(new LayoutParams(android.view.ViewGroup.LayoutParams.WRAP_CONTENT,
                android.view.ViewGroup.LayoutParams.WRAP_CONTENT));

        date.setText(listText.getDate());

        textLayout.addView(date, new TableRow.LayoutParams(
                android.view.ViewGroup.LayoutParams.FILL_PARENT,
                android.view.ViewGroup.LayoutParams.WRAP_CONTENT));

        iLocation = new TextView(context);
        iLocation.setTextColor(Color.GRAY);
        iLocation.setInputType(InputType.TYPE_TEXT_FLAG_CAP_WORDS);
        iLocation.setText(listText.getLocation());
        textLayout.addView(iLocation, new TableRow.LayoutParams(
                android.view.ViewGroup.LayoutParams.FILL_PARENT,
                android.view.ViewGroup.LayoutParams.WRAP_CONTENT));
        
        mCategories = new TextView(context);
        mCategories.setTextColor(Color.GRAY);
       
        
        mCategories.setSingleLine(true);
        mCategories.setEllipsize(TextUtils.TruncateAt.END);
        mCategories.setText(listText.getCategories());
        
        textLayout.addView(mCategories, new TableRow.LayoutParams(
                android.view.ViewGroup.LayoutParams.FILL_PARENT,
                android.view.ViewGroup.LayoutParams.WRAP_CONTENT));
        
        status = new TextView(context);
        status.setText(listText.getStatus());
        
        // change color to red if text is not Verified
        if (listText.getStatus().equalsIgnoreCase("Verified")) {
            status.setTextColor(Color.rgb(41, 142, 40)); // green color
           
        } else if (listText.getStatus().equalsIgnoreCase("Unverified")) {
            status.setTextColor(Color.rgb(237, 0, 0)); // red color
            
        }
        status.setTextSize(fontSize);

        textLayout.addView(status, new TableRow.LayoutParams(
                android.view.ViewGroup.LayoutParams.WRAP_CONTENT,
                android.view.ViewGroup.LayoutParams.FILL_PARENT));

        tblRow.addView(textLayout);

        this.id = listText.getId();

        this.arrow = new ImageView(context);

        this.arrow.setImageDrawable(listText.getArrow());

        arrow.setPadding(2, 25, 10, 2);

        arrow.setLayoutParams(new TableRow.LayoutParams(
                android.view.ViewGroup.LayoutParams.WRAP_CONTENT,
                android.view.ViewGroup.LayoutParams.WRAP_CONTENT));

        tblRow.addView(arrow);

        tblLayout.addView(tblRow);

        addView(tblLayout, new LinearLayout.LayoutParams(
                android.view.ViewGroup.LayoutParams.FILL_PARENT,
                android.view.ViewGroup.LayoutParams.WRAP_CONTENT));

    }

    public void setThumbnail(Drawable thumbnail) {
        this.thumbnail.setImageDrawable(thumbnail);
    }

    public void setThumbnailUri(Uri uri) {
        this.thumbnail.setImageURI(uri);
    }

    public void setTitle(String title) {
        this.title.setText(title);
    }

    public void setDate(String date) {
        this.date.setText(date);
    }

    public void setStatus(String status) {
        this.status.setText(status);
    }

    public void setDesc(String description) {
        this.description = description;
    }

    public void setCategories(String categories) {
        this.mCategories.setText(categories);
    }

    public void setLocation(String location) {
        this.iLocation.setText(location);
    }

    public void setMedia(String media) {
        this.media = media;
    }

    @Override
    public void setId(int id) {
        this.id = id;
    }

    public void setArrow(Drawable arrow) {
        this.arrow.setImageDrawable(arrow);
    }
    
    public void setStatusColor(int color) {
        this.status.setTextColor(color);
    }
    
}
