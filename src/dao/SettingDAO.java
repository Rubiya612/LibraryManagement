package dao;

import model.Setting;
import util.DBConnection;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class SettingDAO {
    public List<Setting> getAllSettings() {
        List<Setting> settings = new ArrayList<>();
        String query = "SELECT setting_key, setting_value FROM settings";

        try (Connection conn = DBConnection.getConnection();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(query)) {

            while (rs.next()) {
                settings.add(new Setting(rs.getString("setting_key"), rs.getString("setting_value")));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return settings;
    }

    public void updateSetting(String key, String value) {
        String query = "UPDATE settings SET setting_value = ? WHERE setting_key = ?";

        try (Connection conn = DBConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(query)) {

            stmt.setString(1, value);
            stmt.setString(2, key);
            stmt.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
