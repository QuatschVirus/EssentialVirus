package de.quatschvirus.essentialvirus.utils;

import org.bukkit.block.BlockFace;
import org.bukkit.block.data.type.Stairs;

public class Directions {
    public static float Convert(BlockFace from) {
        return switch (from) {
            case NORTH -> 180;
            case EAST -> -90;
            case WEST -> 90;
            default -> 0;
        };
    }

    public static float ConvertStairsToFaceRotation(Stairs stairs) {
        return Convert(stairs.getFacing()) + switch (stairs.getShape()) {
            default -> 0;
            case INNER_RIGHT, OUTER_RIGHT -> 45;
            case INNER_LEFT, OUTER_LEFT -> -45;
        };
    }

    public static float SnapToCardinal(float yaw) {
        return Math.round(yaw / 90F) * 90F;
    }
}
