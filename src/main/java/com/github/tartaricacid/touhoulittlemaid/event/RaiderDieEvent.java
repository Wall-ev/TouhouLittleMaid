package com.github.tartaricacid.touhoulittlemaid.event;

import com.github.tartaricacid.touhoulittlemaid.entity.passive.EntityMaid;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.util.Mth;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.entity.raid.Raid;
import net.minecraft.world.entity.raid.Raider;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.GameRules;
import net.minecraftforge.event.entity.living.LivingDeathEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

@Mod.EventBusSubscriber
public class RaiderDieEvent {

    @SubscribeEvent
    public static void giveBadOmenEffect(LivingDeathEvent event) {

        Entity dieEntity = event.getEntity();
        Entity sourceEntity = event.getSource().getEntity();

        if (!(dieEntity instanceof Raider raider && sourceEntity instanceof EntityMaid maid)) {
            return;
        }

        if (!(maid.isTame() && maid.getOwner() instanceof Player owner && owner.isAlive())) {
            return;
        }

        Raid raid = raider.getCurrentRaid();
        if (raid != null) {
            if (raider.isPatrolLeader()) {
                raid.removeLeader(raider.getWave());
            }

            if (owner.getType() == EntityType.PLAYER) {
                raid.addHeroOfTheVillage(owner);
            }

            raid.removeFromRaid(raider, false);
        }

        if (raider.isPatrolLeader() && raid == null && ((ServerLevel) raider.level()).getRaidAt(raider.blockPosition()) == null) {
            ItemStack itemstack = raider.getItemBySlot(EquipmentSlot.HEAD);
            Player player = owner;

            if (!itemstack.isEmpty() && ItemStack.matches(itemstack, Raid.getLeaderBannerInstance()) && player != null) {
                MobEffectInstance mobeffectinstance1 = player.getEffect(MobEffects.BAD_OMEN);
                int i = 1;
                if (mobeffectinstance1 != null) {
                    i += mobeffectinstance1.getAmplifier();
                    player.removeEffectNoUpdate(MobEffects.BAD_OMEN);
                } else {
                    --i;
                }

                i = Mth.clamp(i, 0, 4);
                MobEffectInstance mobeffectinstance = new MobEffectInstance(MobEffects.BAD_OMEN, 120000, i, false, false, true);
                if (!raider.level().getGameRules().getBoolean(GameRules.RULE_DISABLE_RAIDS)) {
                    player.addEffect(mobeffectinstance);
                }
            }
        }
    }
}
