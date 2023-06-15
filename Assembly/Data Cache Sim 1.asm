# Data Cache Simulation 1: 2 Variables

.globl main

.data
yourname1:	.asciiz 	"xyz1xyz1"
.align 64
yourname2:	.asciiz 	"xyz2xyz2"

.text
main:
    li $t2, 0               # Initialize loop counter for entire program

outerLoop:
    beq $t2, 2, Exit        # If we have run the whole program twice, go to Exit

	la $s0, yourname1		# Load address of yourname1 into $s0
	la $s1, yourname2       # Load address of yourname2 into $s1
	
	li $t0, 0               # Initialize loop counter

Loop1:	
	beq $t0, 4, LoopReset  	# If we have loaded 4 words, go to Loop2
	
	lw $t1, 0($s0)         	# Load word from yourname1
	addiu $s0, $s0, 4      	# Move to the next word

	addiu $t0, $t0, 1      	# Increment loop counter
	j Loop1                	# Jump to the start of Loop1

LoopReset:
	li $t0, 0              	# Reset loop counter

Loop2:
	beq $t0, 4, LoopReset2  	# If we have loaded 4 words, go to Exit
	
	lw $t1, 0($s1)        	# Load word from yourname2
	addiu $s1, $s1, 4      	# Move to the next word

	addiu $t0, $t0, 1      	# Increment loop counter
	j Loop2                	# Jump to the start of Loop2

LoopReset2:
	li $t0, 0              	# Reset loop counter

innerLoopExit:
    addiu $t2, $t2, 1       # Increment outer loop counter
    j outerLoop             # Jump to the start of outer loop

Exit:



# =========================={ Notes }========================== #
# No syscalls to not skew the results from using data caches.
# Use LRU rather than random replacement so results are consistant.

# ========================{ Questions }======================== #
# Set up the data cache parameters of the Data Cache Simulator such that:
# 
# 1.The two arrays map directly on top of one another.
#   With a block size of one (1) and Direct Mapped, run the simulator.
#   What is the hit rate? - 0%
#
# 2.Still Direct Mapped, what is the hit rate with a block size of two (2)? - 50%
#   (Remember, since we are testing cache performance, the cache size must always remain the same!
#   Make whatever adjustments are necessary so that the cache size always remains the same,
#   otherwise you are comparing apples and oranges.)
#
# 3.As in 2 directly above, but with a block size of four (4), what is the hit rate? - 75%
# 
# 4.Configure the cache as 2-way set associative with a block size
#   of one and iterate twice through the data loading sequence
#   (put a loop around the original sequence). What is the hit rate? - 50%
# 
# 5.Configure the cache as 4-way set associative with a block size
#   of one and iterate twice through the data loading sequence.
#   What is the hit rate? - 50%
#
# 6.As in 5 above, but fully associative. What is the hit rate? - 50%
#
# 7.If you run the program again without resetting the
#   cache simulator what is the hit rate? - 0%
# 
# 8.If you repeat 7 above 100 times what will be the
#   hit rate rounded to the 1’s place. - 100%
