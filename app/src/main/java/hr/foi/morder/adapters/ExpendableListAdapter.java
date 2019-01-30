package hr.foi.morder.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseExpandableListAdapter;
import android.widget.TextView;

import java.util.HashMap;
import java.util.List;

import hr.foi.morder.R;

/**
 * The type Expendable list adapter.
 */
public class ExpendableListAdapter extends BaseExpandableListAdapter {
    private Context ctx;
    private List<String> headerList;
    private HashMap<String, List<String>> dataChildList;

    /**
     * Instantiates a new Expendable list adapter.
     *
     * @param context    the context
     * @param listHeader the list header
     * @param data       the data
     */
    public ExpendableListAdapter(Context context, List<String> listHeader, HashMap<String, List<String>> data) {
        this.ctx = context;
        this.headerList = listHeader;
        this.dataChildList = data;
    }

    @Override
    public int getGroupCount() {
        int headerCount = headerList.size();
        return headerCount;
    }

    @Override
    public int getChildrenCount(int groupPosition) {
        return this.dataChildList.get(this.headerList.get(groupPosition)).size();
    }

    @Override
    public Object getGroup(int groupPosition) {
        return this.headerList.get(groupPosition);
    }

    @Override
    public String getChild(int groupPosition, int childPosition) {
        return this.dataChildList.get(this.headerList.get(groupPosition)).get(childPosition);
    }

    @Override
    public long getGroupId(int groupPosition) {
        return groupPosition;
    }

    @Override
    public long getChildId(int groupPosition, int childPosition) {
        return childPosition;
    }

    @Override
    public boolean hasStableIds() {
        return false;
    }

    @Override
    public View getGroupView(int groupPosition, boolean isExpanded, View convertView, ViewGroup parent) {
        String header = (String) getGroup(groupPosition);
        if (convertView == null) {
            LayoutInflater layoutInflater = (LayoutInflater) this.ctx.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = layoutInflater.inflate(R.layout.nav_header_name, null);
        }
        TextView textHeader = convertView.findViewById(R.id.submenu);
        textHeader.setText(header);
        return convertView;
    }

    @Override
    public View getChildView(int groupPosition, int childPosition, boolean isLastChild, View convertView, ViewGroup parent) {
        final String child = getChild(groupPosition, childPosition);
        if (convertView == null) {
            LayoutInflater layoutInflater = (LayoutInflater) this.ctx.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = layoutInflater.inflate(R.layout.submenu, null);
        }
        TextView textChild = convertView.findViewById(R.id.submenu);
        textChild.setText(child);
        return convertView;
    }

    @Override
    public boolean isChildSelectable(int groupPosition, int childPosition) {
        return true;
    }
}