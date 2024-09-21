package com.github.tartaricacid.touhoulittlemaid.api;

import com.github.tartaricacid.touhoulittlemaid.block.multiblock.MultiBlockManager;
import com.github.tartaricacid.touhoulittlemaid.client.renderer.entity.EntityMaidRenderer;
import com.github.tartaricacid.touhoulittlemaid.client.renderer.entity.GeckoEntityMaidRenderer;
import com.github.tartaricacid.touhoulittlemaid.entity.backpack.BackpackManager;
import com.github.tartaricacid.touhoulittlemaid.entity.task.TaskManager;
import com.github.tartaricacid.touhoulittlemaid.entity.task.meal.MaidMealManager;
import com.github.tartaricacid.touhoulittlemaid.inventory.chest.ChestManager;
import com.github.tartaricacid.touhoulittlemaid.item.bauble.BaubleManager;
import me.shedaniel.clothconfig2.api.ConfigBuilder;
import me.shedaniel.clothconfig2.api.ConfigEntryBuilder;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.world.entity.Mob;

public interface ILittleMaid {
    /**
     * 为物品绑定女仆饰品属性
     *
     * @param manager 注册器
     */
    default void bindMaidBauble(BaubleManager manager) {
    }

    /**
     * 添加女仆的 Task
     *
     * @param manager 注册器
     */
    default void addMaidTask(TaskManager manager) {
    }

    /**
     * 添加女仆的背包
     *
     * @param manager 注册器
     */
    default void addMaidBackpack(BackpackManager manager) {
    }

    /**
     * 添加多方块结构
     *
     * @param manager 注册器
     */
    default void addMultiBlock(MultiBlockManager manager) {
    }

    /**
     * 添加箱子类型，用于隙间饰品的箱子识别
     *
     * @param manager 注册器
     */
    default void addChestType(ChestManager manager) {
    }

    /**
     * 添加女仆饭类型
     *
     * @param manager 注册器
     */
    default void addMaidMeal(MaidMealManager manager) {
    }

    /**
     * 绑定配置文件
     */
    default void addClothConfigEntry(ConfigBuilder root, ConfigEntryBuilder entryBuilder) {
    }

    /**
     * 添加默认实体渲染
     */
    default void addEntityMaidRenderer(EntityMaidRenderer entityMaidRenderer, EntityRendererProvider.Context renderManager) {
    }

    /**
     * 添加Gecko实体渲染
     */
    default void addGeckoEntityMaidRenderer(GeckoEntityMaidRenderer<? extends Mob> geckoEntityMaidRenderer, EntityRendererProvider.Context renderManager) {
    }
}
