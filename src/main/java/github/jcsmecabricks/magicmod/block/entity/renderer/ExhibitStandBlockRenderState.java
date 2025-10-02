package github.jcsmecabricks.magicmod.block.entity.renderer;

import net.minecraft.client.render.item.ItemRenderState;
import net.minecraft.client.render.block.entity.state.BlockEntityRenderState;
import net.minecraft.item.ItemStack;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

public class ExhibitStandBlockRenderState extends BlockEntityRenderState {
    public ItemStack itemStack = ItemStack.EMPTY;
    public float renderingRotation = 0f;
    private int light = 0;
    private World world;
    public BlockPos pos;

    public ItemStack getItemStack() {
        return itemStack;
    }

    public ExhibitStandBlockRenderState setItemStack(ItemStack stack) {
        this.itemStack = stack;
        return this;
    }

    public float getRenderingRotation() {
        return renderingRotation;
    }

    public ExhibitStandBlockRenderState setRenderingRotation(float rotation) {
        this.renderingRotation = rotation;
        return this;
    }

    public int getLight() {
        return light;
    }

    public ExhibitStandBlockRenderState setLight(int light) {
        this.light = light;
        return this;
    }

    public World getWorld() {
        return world;
    }

    public ExhibitStandBlockRenderState setWorld(World world) {
        this.world = world;
        return this;
    }

    public BlockPos getPos() {
        return pos;
    }

    public ExhibitStandBlockRenderState setPos(BlockPos pos) {
        this.pos = pos;
        return this;
    }

    public long getRandomSeed() {
        return (pos != null) ? pos.asLong() : 42L;
    }

    public ItemRenderState.Glint getGlint() {
        // You can add logic to return glint state based on item properties
        return ItemRenderState.Glint.NONE;
    }
}
