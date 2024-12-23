package top.alittlebot.grass_craft.datagen.provider;

import net.minecraft.data.PackOutput;
import net.neoforged.neoforge.common.data.LanguageProvider;
import top.alittlebot.grass_craft.GrassCraft;
import top.alittlebot.grass_craft.effect.GrassEffects;
import top.alittlebot.grass_craft.ui.GrassCreativeTab;
import top.alittlebot.grass_craft.entity.GrassEntity;
import top.alittlebot.grass_craft.item.GrassItems;
import top.alittlebot.grass_craft.item.potion.GrassPotions;

public class GrassLanguageProvider {

    public static class EnglishLanguageProvider extends LanguageProvider {
        public EnglishLanguageProvider(PackOutput gen) {
            super(gen, GrassCraft.MOD_ID, "en_us");
        }

        @Override
        protected void addTranslations() {
            this.add(GrassItems.GRASS_STICK_ITEM.get(), "Grass Stick");
            this.add(GrassItems.STAFF_OF_GRASS_ITEM.get(), "Staff of Grass");
            this.add(GrassItems.GRASS_BALL_ITEM.get(), "Grass Ball");
            this.add(GrassItems.VANILLA_ITEM.get(), "Vanilla");
            this.add(GrassItems.VANILLA_INGOT_ITEM.get(), "Vanilla Ingot");
            this.add(GrassItems.VANILLA_GLOVE_ITEM.get(), "Vanilla Glove");
            this.add(GrassItems.VANILLA_PUREE_ITEM.get(), "Vanilla Puree");
            this.add(GrassItems.GRASS_MOB_SPAWN_EGG_ITEM.get(), "Grass Mob Spawn Egg");
            this.add(GrassItems.GRASS_ON_A_STICK_ITEM.get(), "Grass on a Stick");
            this.add(GrassItems.WEEDS_ITEM.get(), "Weeds");
            this.add(GrassItems.GRASS_FISH_ITEM.get(), "Grass Fish");
            this.add(GrassItems.GRASS_HAT_ITEM.get(), "Grass Hat");
            this.add(GrassItems.GRASS_LLAMA_SPAWN_EGG_ITEM.get(), "Grass Llama Spawn Egg");

            this.add(GrassItems.GRASS_TNT_ITEM.get(), "Grass TNT");

            this.add(GrassItems.VANILLA_ROD_ITEM.get(), "Vanilla Rod");
            this.add(GrassItems.COOKED_GRASS_BLOCK_ITEM.get(), "Cooked Grass Block");

            this.add(GrassEffects.GROW_GRASS.get(), "Grow Grass");
            this.add(GrassEffects.TO_GRASS.get(), "To Grass");
            this.add(GrassEffects.GRASS_POISONING.get(), "Grass Poisoning");

            this.add(GrassPotions.POTION_KEY + GrassPotions.GRASS_POTION_ID, "Potion of Grass");
            this.add(GrassPotions.SPLASH_POTION_KEY + GrassPotions.GRASS_POTION_ID, "Splash Potion of Grass");
            this.add(GrassPotions.LINGERING_POTION_KEY + GrassPotions.GRASS_POTION_ID, "Lingering Potion of Grass");
            this.add(GrassPotions.TIPPED_ARROW_KEY + GrassPotions.GRASS_POTION_ID, "Arrow of Grass");

            this.add(GrassPotions.POTION_KEY + GrassPotions.TO_GRASS_POTION_ID, "Potion of To Grass");
            this.add(GrassPotions.SPLASH_POTION_KEY + GrassPotions.TO_GRASS_POTION_ID, "Splash Potion of To Grass");
            this.add(GrassPotions.LINGERING_POTION_KEY + GrassPotions.TO_GRASS_POTION_ID, "Lingering Potion of To Grass");
            this.add(GrassPotions.TIPPED_ARROW_KEY + GrassPotions.TO_GRASS_POTION_ID, "Arrow of To Grass");

            this.add(GrassPotions.POTION_KEY + GrassPotions.GRASS_POISONING_POTION_ID, "Potion of Grass Poisoning");
            this.add(GrassPotions.SPLASH_POTION_KEY + GrassPotions.GRASS_POISONING_POTION_ID, "Splash Potion of Grass Poisoning");
            this.add(GrassPotions.LINGERING_POTION_KEY + GrassPotions.GRASS_POISONING_POTION_ID, "Lingering Potion of Grass Poisoning");
            this.add(GrassPotions.TIPPED_ARROW_KEY + GrassPotions.GRASS_POISONING_POTION_ID, "Arrow of Grass Poisoning");

            this.add(GrassPotions.POTION_KEY + GrassPotions.STRONG_GRASS_POISONING_POTION_ID, "Potion of Grass Poisoning");
            this.add(GrassPotions.SPLASH_POTION_KEY + GrassPotions.STRONG_GRASS_POISONING_POTION_ID, "Splash Potion of Grass Poisoning");
            this.add(GrassPotions.LINGERING_POTION_KEY + GrassPotions.STRONG_GRASS_POISONING_POTION_ID, "Lingering Potion of Grass Poisoning");
            this.add(GrassPotions.TIPPED_ARROW_KEY + GrassPotions.STRONG_GRASS_POISONING_POTION_ID, "Arrow of Grass Poisoning");

            this.add(GrassEntity.GRASS_TNT_ENTITY.get(), "Primed Grass TNT");
            this.add(GrassEntity.GRASS_MOB_ENTITY.get(), "Grass Mob");
            this.add(GrassEntity.GRASS_BALL_ENTITY.get(), "Grass Ball");
            this.add(GrassEntity.GRASS_LLAMA_ENTITY.get(), "Grass Llama");

            this.add("grass_craft.tooltip.vanilla_glove", "Idea from Mafuyu33, only works on grass");
            this.add("grass_craft.tooltip.vanilla_rod", "That must be fun ヾ(≧▽≦*)o");

            this.add(GrassCreativeTab.GRASS_TAB_ID, "Grass Craft");
        }
    }


    public static class ChineseLanguageProvider extends LanguageProvider {
        public ChineseLanguageProvider(PackOutput gen) {
            super(gen, GrassCraft.MOD_ID, "zh_cn");
        }

        @Override
        protected void addTranslations() {
            this.add(GrassItems.GRASS_STICK_ITEM.get(), "草棍");
            this.add(GrassItems.STAFF_OF_GRASS_ITEM.get(), "草之杖");
            this.add(GrassItems.GRASS_BALL_ITEM.get(), "草球");
            this.add(GrassItems.VANILLA_ITEM.get(), "香草");
            this.add(GrassItems.VANILLA_INGOT_ITEM.get(), "香草锭");
            this.add(GrassItems.VANILLA_GLOVE_ITEM.get(), "香草手套");
            this.add(GrassItems.VANILLA_PUREE_ITEM.get(), "香草泥");
            this.add(GrassItems.GRASS_MOB_SPAWN_EGG_ITEM.get(), "草草草刷怪蛋");
            this.add(GrassItems.GRASS_ON_A_STICK_ITEM.get(), "草钓竿");
            this.add(GrassItems.WEEDS_ITEM.get(), "杂草");
            this.add(GrassItems.GRASS_FISH_ITEM.get(), "草鱼");
            this.add(GrassItems.GRASS_HAT_ITEM.get(), "草帽");
            this.add(GrassItems.GRASS_LLAMA_SPAWN_EGG_ITEM.get(), "草泥马刷怪蛋");

            this.add(GrassItems.GRASS_TNT_ITEM.get(), "香草TNT");

            this.add(GrassItems.VANILLA_ROD_ITEM.get(), "香草烛");
            this.add(GrassItems.COOKED_GRASS_BLOCK_ITEM.get(), "熟草方块");

            this.add(GrassEffects.GROW_GRASS.get(), "生草");
            this.add(GrassEffects.TO_GRASS.get(), "变草");
            this.add(GrassEffects.GRASS_POISONING.get(), "草中毒");

            this.add(GrassPotions.POTION_KEY + GrassPotions.GRASS_POTION_ID, "生草药水");
            this.add(GrassPotions.SPLASH_POTION_KEY + GrassPotions.GRASS_POTION_ID, "喷溅型生草药水");
            this.add(GrassPotions.LINGERING_POTION_KEY + GrassPotions.GRASS_POTION_ID, "滞留型生草药水");
            this.add(GrassPotions.TIPPED_ARROW_KEY + GrassPotions.GRASS_POTION_ID, "生草之箭");

            this.add(GrassPotions.POTION_KEY + GrassPotions.TO_GRASS_POTION_ID, "变草药水");
            this.add(GrassPotions.SPLASH_POTION_KEY + GrassPotions.TO_GRASS_POTION_ID, "喷溅型变草药水");
            this.add(GrassPotions.LINGERING_POTION_KEY + GrassPotions.TO_GRASS_POTION_ID, "滞留型变草药水");
            this.add(GrassPotions.TIPPED_ARROW_KEY + GrassPotions.TO_GRASS_POTION_ID, "变草之箭");

            this.add(GrassPotions.POTION_KEY + GrassPotions.GRASS_POISONING_POTION_ID, "草中毒药水");
            this.add(GrassPotions.SPLASH_POTION_KEY + GrassPotions.GRASS_POISONING_POTION_ID, "喷溅型草中毒药水");
            this.add(GrassPotions.LINGERING_POTION_KEY + GrassPotions.GRASS_POISONING_POTION_ID, "滞留型草中毒药水");
            this.add(GrassPotions.TIPPED_ARROW_KEY + GrassPotions.GRASS_POISONING_POTION_ID, "草中毒之箭");

            this.add(GrassPotions.POTION_KEY + GrassPotions.STRONG_GRASS_POISONING_POTION_ID, "草中毒药水");
            this.add(GrassPotions.SPLASH_POTION_KEY + GrassPotions.STRONG_GRASS_POISONING_POTION_ID, "喷溅型草中毒药水");
            this.add(GrassPotions.LINGERING_POTION_KEY + GrassPotions.STRONG_GRASS_POISONING_POTION_ID, "滞留型草中毒药水");
            this.add(GrassPotions.TIPPED_ARROW_KEY + GrassPotions.STRONG_GRASS_POISONING_POTION_ID, "草中毒之箭");

            this.add(GrassEntity.GRASS_TNT_ENTITY.get(), "点燃的香草TNT");
            this.add(GrassEntity.GRASS_MOB_ENTITY.get(), "草草草");
            this.add(GrassEntity.GRASS_BALL_ENTITY.get(), "草球");
            this.add(GrassEntity.GRASS_LLAMA_ENTITY.get(), "草泥马");

            this.add("grass_craft.tooltip.vanilla_glove", "想法来自马夫鱼33, 只能对草起作用");
            this.add("grass_craft.tooltip.vanilla_rod", "那一定很好玩吧…… ヾ(≧▽≦*)o");

            this.add(GrassCreativeTab.GRASS_TAB_ID, "草工艺");
        }
    }
}
