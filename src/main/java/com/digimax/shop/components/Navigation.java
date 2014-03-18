package com.digimax.shop.components;

import com.digimax.shop.pages.report.GeneralLedgerReport;
import com.digimax.shop.pages.report.InventoryReport;
import org.apache.commons.lang.WordUtils;
import org.apache.tapestry5.ComponentResources;
import org.apache.tapestry5.annotations.Property;
import org.apache.tapestry5.ioc.Messages;
import org.apache.tapestry5.ioc.annotations.Inject;
import org.apache.tapestry5.services.PageRenderLinkSource;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by jon on 2014-03-18.
 */
public class Navigation {

    @Inject
    private PageRenderLinkSource pageRenderLinkSource;

    @Inject
    private ComponentResources componentResources;

    @Inject
    private Messages messages;

    @Property
    private MenuItem menuItem;

    @Property
    private MenuItem subMenuItem;

    public boolean hasChildren() {
        return menuItem.getChildren()!=null && menuItem.getChildren().size()>0;
    }

    public String getSubCssClass() {
        return subMenuItem.isDivider()? "divider": "";
    }

    public String getCssClass() {
        if (hasChildren()) {
            return "dropdown";
        }
        return (menuItem.isActive(componentResources.getPageName()))?"active":"";
    }

    public List<MenuItem> getMenuItems() {
        List<MenuItem> menuItems = new ArrayList<>();
        MenuItem shop = new MenuItemImpl(pageRenderLinkSource.createPageRenderLink(
                com.digimax.shop.pages.shopping.Index.class).toAbsoluteURI(), messages.get("shop"));
        MenuItem receiving = new MenuItemImpl(pageRenderLinkSource.createPageRenderLink(
                com.digimax.shop.pages.receiving.Index.class).toAbsoluteURI(), messages.get("receiving"));
        MenuItem report = new MenuItemImpl(pageRenderLinkSource.createPageRenderLink(
                com.digimax.shop.pages.report.Index.class).toAbsoluteURI(), messages.get("report"));

        MenuItem inventoryReport = new MenuItemImpl(pageRenderLinkSource.createPageRenderLink(
                InventoryReport.class).toAbsoluteURI(), messages.get("inventory-report"));

        MenuItem generalLedgerReport = new MenuItemImpl(pageRenderLinkSource.createPageRenderLink(
                GeneralLedgerReport.class).toAbsoluteURI(), messages.get("general-ledger-report"));


        report.getChildren().add(inventoryReport);
        report.getChildren().add(DIVIDER);
        report.getChildren().add(generalLedgerReport);
        menuItems.add(shop);
        menuItems.add(receiving);
        menuItems.add(report);
        return menuItems;
    }

    public static final class MenuItemImpl implements MenuItem {
        private String displayLabel;
        private String pageName;
        private List<MenuItem> children;

        public MenuItemImpl(String pageName, String displayLabel) {
            this.displayLabel = displayLabel;
            this.pageName = pageName;
        }

        @Override
        public boolean isActive(String activePageName) {
            activePageName = activePageName.contains("/")? activePageName.substring(0, activePageName.indexOf('/'))
                    : activePageName;
//            if (activePageName.equals("settings") && pageName.contains("user")) {
//                return true;
//            }
            return pageName.contains(activePageName);
        }

        @Override
        public String getPageName() {
            return pageName;
        }

        @Override
        public String getFormattedPageName() {
            return displayLabel!=null? displayLabel.toUpperCase(): getPageName().toUpperCase();
        }

        @Override
        public String getCapitalizedPageName() {
            return WordUtils.capitalizeFully(getFormattedPageName());
        }

        //Always use this getter instead of directly handling the children attribute
        @Override
        public List<MenuItem> getChildren() {
            if (children==null) {
                children = new ArrayList<>();
            }
            return children;
        }

        @Override
        public boolean isDivider() {
            return false;
        }
    }

    public static interface MenuItem {
        List<MenuItem> getChildren();
        boolean isActive(String activePageName);
        boolean isDivider();
        String getFormattedPageName();
        String getCapitalizedPageName();
        String getPageName();
    }

    private static final MenuItem DIVIDER = new MenuItem() {
        @Override
        public List<MenuItem> getChildren() {
            return null;
        }
        @Override
        public boolean isActive(String activePageName) {
            return false;
        }
        @Override
        public boolean isDivider() {
            return true;
        }
        @Override
        public String getFormattedPageName() {
            return null;
        }
        @Override
        public String getCapitalizedPageName() {
            return null;
        }
        @Override
        public String getPageName() {
            return null;
        }
    };
}
