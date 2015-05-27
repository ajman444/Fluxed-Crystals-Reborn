package fluxedCrystals.recipe;

import net.minecraft.item.ItemStack;
import net.minecraftforge.oredict.OreDictionary;

import java.util.ArrayList;

public class RecipeGemRefiner
{

	private ItemStack input;
	private ItemStack output;
	private int inputAmount;
	private int outputAmount;

	public RecipeGemRefiner (ItemStack input, ItemStack output, int inputAmount, int outputAmount) {
		this.input = input;
		this.output = output;
		this.inputAmount = inputAmount;
		this.outputAmount = outputAmount;
	}

	public int getOutputAmount () {
		return outputAmount;
	}

	public boolean matches (ItemStack stack) {
		int[] ids = OreDictionary.getOreIDs(stack);
		for (int id : ids) {
			String name = OreDictionary.getOreName(id);
			if (matches(name)) {
				return true;
			}
		}
		return stack != null && OreDictionary.itemMatches(stack, input, false);
	}

	private boolean matches (String oreDict) {
		ArrayList<ItemStack> stacks = OreDictionary.getOres(oreDict);
		for (ItemStack stack : stacks) {
			if (OreDictionary.itemMatches(stack, input, false)) {
				return true;
			}
		}
		return false;
	}

	public boolean matchesExact (ItemStack stack) {
		return stack != null && input.isItemEqual(stack);
	}

	public ItemStack getInput () {
		return input.copy();
	}

	public ItemStack getOutput () {
		return output.copy();
	}

	public int getInputamount () {
		return inputAmount;
	}
}
