# Hedwig's New Hit Single

.globl main

.data
# Note pitch values
pitch_array: .word 71, 76, 79, 78, 76, 83, 81, 78,
    		       76, 79, 78, 75, 77, 71,
    		   71, 76, 79, 78, 76, 83, 87, 86, 85, 82
    		       85, 84, 83, 70, 79, 76
    			   
# Note durations in milliseconds
duration_array: .word 548, 365, 137, 274, 548, 274, 822, 822,
    		           365, 137, 274, 548, 274, 1370,
    		      548, 365, 137, 274, 548, 274, 548, 365, 548, 365,
    		           365, 137, 274, 548, 274, 1370

.text
main:
jal play_music_loop
jal exit_program

play_music_loop:
	lw $a0, pitch_array($s0)		# Load pitch value
	lw $a1, duration_array($s0)		# Load duration value

	li $v0, 31						# Service 31 for Audio Out
	li $a2, 6						# Instrument (Piano 7)
	li $a3, 100						# Volume (0-127)
	syscall

	li $v0, 32						# Service 32 to sleep
 	move $a0, $a1					# Set sleep duration to the note duration
	syscall

	addi $s0, $s0, 4				# Move to the next note (byte)
	blt $s0, 120, play_music_loop	# 30 Notes * 4 = Play for 120 notes
	jr $ra

exit_program:
	li $v0, 10						# Use service 10 to exit
	syscall


# Notes
# B, E, G, F#, E, B, A, F#,
#    E, G, F#, Eb, F, B
# B, E, G, F#, E, B, D, Db, C
#    Ab, C, B, Bb, G, E

# 48 BPM
# Semi Quaver (sixteenth note) - 137 ms
# Quaver (eigth note) - 274 ms
# Dotted Quavter - 365 ms
# Crotchet (quarter note): 548 ms
# Dotted Crotchet: 822 ms
# 5 Quavers: 1370 ms